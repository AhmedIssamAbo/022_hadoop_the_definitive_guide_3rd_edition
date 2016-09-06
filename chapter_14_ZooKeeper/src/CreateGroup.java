/**
 * Filename: CreateGroup.jsp
 * Author:   jerry_0824
 * Email:    63935127#qq.com
 * Date:     2016-09-06
 * Time:     22:27
 * Version:  v1.0.0
 */
public class CreateGroup {
    private static final int SESSION_TIMEOUT = 5000;

    private ZooKeeper zk;
    private CountDownLatch connectedSignal = new CountDownLatch(1);

    public void connect(String hosts) throws IOException, InterruptedException {
        zk = new ZooKeeper(hosts, SESSION_TIMEOUT, this);
        connectedSignal.await();
    }

    @Override
    public void process(WatchedEvent event) {
        // Watcher interface
        if (event.getState() == KeeperState.SyncConnected) {
            connectedSignal.countDown();
        }
    }

    public void create(String groupName) throws KeeperException, InterruptedException {
        String path = "/" + groupName;
        String createdPath = zk.create(path, null /* data */, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        System.out.println("Created " + createdPath);
    }

    public void close() throws InterruptedException {
        zk.close();
    }

    public static void main(String[] args) throws Exception {
        CreateGroup createGroup = new CreateGroup();

        createGroup.connect(args[0]);
        createGroup.create(args[1]);

        createGroup.close();
    }
}
