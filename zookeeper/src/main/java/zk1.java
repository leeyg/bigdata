import org.apache.zookeeper.*;

public class zk1 {
    private static String connectString="192.168.100.10:2181";
    private static int sessionTimeout=6000;
    public static void main(String[] args) throws Exception{
        Watcher watcher=new Watcher(){
            public void process(WatchedEvent event) {
                System.out.println("监听到的事件："+event);
            }
        };
        final ZooKeeper zookeeper=new ZooKeeper(connectString,sessionTimeout,watcher);
        System.out.println("获得连接："+zookeeper);
        zookeeper.create("/chroot",null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        final byte[] data=zookeeper.getData("/test", watcher, null);
        System.out.println("读取的值："+new String(data));
        zookeeper.close();
    }
}  