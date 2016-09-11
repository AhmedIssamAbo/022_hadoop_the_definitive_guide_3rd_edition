/**
 * Filename: cpp_get_max_temperature.cpp
 * Author:   jerry_0824
 * Email:    63935127#qq.com
 * Date:     2016-09-09
 * Time:     13:47
 * Version:  v1.0.0
 */

#include <algorithm>
#include <limits>
#include <stdint.h>
#include <string>

#include "hadoop/Pipes.hh"
#include "hadoop/TemplateFactory.hh"
#include "hadoop/StringUtils.hh"

class MaxTemperatureMapper : public HadoopPipes::Mapper {
public:
    MaxTemperatureMapper(HadoopPipes::TaskContext& context) {
    }

    void map(HadoopPipes::MapContext& context) {
        std::string line = context.getInputValue();
        std::string year = line.substr(15, 4);
        std::string airTemperature = line.substr(87, 5);
        std::string q = line.substr(92, 1);

        if (airTemperature != "+9999" && (q == "0" || q == "1" || q == "4" || q == "5" || q == "9")) {
            context.emit(year, airTemperature);
        }
    }
};

class MaxTemperatureReducer : public HadoopPipes::Reducer {
public:
    MaxTemperatureReducer(HadoopPipes::TaskContext& context) {
    }

    void reduce(HadoopPipes::ReduceContext& context) {
        int maxValue = INT_MIN;

        while (context.nextValue()) {
            maxValue = std::max(maxValue, HadoopUtils::toInt(context.getInputValue()));
        }

        context.emit(context.getInputKey(), HadoopUtils::toString(maxValue));
    }
};

int
main(int argc, char *argv[]) {
    return HadoopPipes::runTask(HadoopPipes::TemplateFactory<MaxTemperatureMapper MaxTemperatureReducer>());
}
