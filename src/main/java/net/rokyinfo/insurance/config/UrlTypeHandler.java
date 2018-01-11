package net.rokyinfo.insurance.config;

import org.apache.http.util.TextUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(String.class)
@MappedJdbcTypes({JdbcType.VARCHAR})
public class UrlTypeHandler extends BaseTypeHandler<String> {

    //    @Value("${insurance.res.image.request.url.prefix}")
    public static final String urlPrefix = "http://localhost/insurance/res/";

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, String str, JdbcType jdbcType) throws SQLException {
        if (!TextUtils.isEmpty(str)) {
            preparedStatement.setString(i, str);
        }
    }

    @Override
    public String getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String str = resultSet.getString(s);
        return handleField(str);
    }

    @Override
    public String getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String str = resultSet.getString(i);
        return handleField(str);
    }

    @Override
    public String getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String str = callableStatement.getString(i);
        return handleField(str);
    }

    private String handleField(String str) {
        if (!TextUtils.isEmpty(str)) {
            return urlPrefix + str;
        }
        return str;
    }

}
