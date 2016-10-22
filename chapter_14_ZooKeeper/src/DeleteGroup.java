/**
 * Filename: DeleteGroup.java
 * Author:   jerry_0824
 * Email:    63935127#qq.com
 * Date:     2016-09-07
 * Time:     8:06
 * Version:  v1.0.0
 */
public class DeleteGroup extends ConnectionWatcher {
    public void delete(String groupName) throws keeperException, InterruptedException {
        String path = "/" + groupName;

        try {
            List<String> children = zk.getChildren(path, false);

            for (String child : children) {
                zk.delete(path + "/" + child, -1);
            }

            zk.delete(path, -1);
        } catch (KeeperException.NoNodeException e) {
            System.out.printf("Group %s does not exist\n", groupName);
            System.exit(1);
        }
    }

    public static void main(String[] args) throws Exception {
        DeleteGroup deleteGroup = new DeleteGroup();

        deleteGroup.connect(args[0]);
        deleteGroup.delete(args[1]);

        deleteGroup.close();
    }
}
