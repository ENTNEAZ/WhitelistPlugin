package top.nacldragon.whitelistplugin.QQGroupUtils;

import com.google.gson.Gson;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class QQGroup {
    private static QQGroup instance;
    public static QQGroup getInstance() {
        if (instance == null) {
            instance = new QQGroup();
        }
        return instance;
    }

    private String group;
    private String miraiAPI;
    public void setGroup(String group) {
        this.group = group;
    }
    public String getGroup() {
        return this.group;
    }
    public void setMiraiAPI(String miraiAPI) {
        this.miraiAPI = miraiAPI;
    }
    public boolean CheckIfUserInGroup(String qq) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(this.miraiAPI + "get_group_member_info?group_id=" + this.group + "&user_id=" + qq);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(2000);
            connection.setReadTimeout(2000);
            connection.connect();

            //获取返回json
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
            }
            bufferedReader.close();
            inputStream.close();

            //解析json
            Gson gson = new Gson();
            MiraiReplyStruct m = gson.fromJson(response.toString(), MiraiReplyStruct.class);
            if (m.getRetcode() == 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
