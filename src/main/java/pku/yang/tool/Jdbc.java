package pku.yang.tool;

import org.springframework.stereotype.Component;

/**
 * Created by LinkedME07 on 16/7/9.
 */
@Component
public class Jdbc {

    public String driverClass;
    public String jdbcUrlPrefix;
    public String host;
    public String port;
    public String dataBase;
    public String user;
    public String password;
    public String useUnicode;
    public String characterEncoding;
    public String useSSL;

    public Jdbc() {}

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public void setJdbcUrlPrefix(String jdbcUrlPrefix) {
        this.jdbcUrlPrefix = jdbcUrlPrefix;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setDataBase(String dataBase) {
        this.dataBase = dataBase;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUseUnicode(String useUnicode) {
        this.useUnicode = useUnicode;
    }

    public void setCharacterEncoding(String characterEncoding) {
        this.characterEncoding = characterEncoding;
    }

    public void setUseSSL(String useSSL) {
        this.useSSL = useSSL;
    }


    @Override
    public String toString() {
        return new StringBuffer(jdbcUrlPrefix).append(host)
                .append(":").append(port).append("/").append(dataBase).append("?user=").append(user)
                .append("&password=").append(password).append("&useUnicode=").append(useUnicode)
                .append("&characterEncoding=").append(characterEncoding).append("&useSSL=").append(useSSL).toString();
    }
}
