package jappo;

public class JappoBuilder {
    private String serverAddress;
    private Integer serverPort;

    public JappoBuilder setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
        return this;
    }

    public JappoBuilder setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
        return this;
    }
    public JappoBuilder configure(String src){

        return this;
    }

    public Jappo createJappo() {
        return new Jappo(serverAddress, serverPort);
    }
}