package net.rokyinfo.insurance.config;

import org.apache.http.util.TextUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.beans.factory.annotation.Value;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(String.class)
@MappedJdbcTypes({JdbcType.VARCHAR})
public class UrlTypeHandler extends BaseTypeHandler<String> {

    public static final String[] URL_FIELD = new String[] {"bill_img"};

    @Value("${insurance.res.image.request.url.prefix}")
    private String urlPrefix;

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, String str, JdbcType jdbcType) throws SQLException {
        if (!TextUtils.isEmpty(str)) {
            preparedStatement.setString(i, str);
        }
    }

    @Override
    public String getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String str = resultSet.getString(s);
        return handleField(str, s);
    }

    @Override
    public String getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String str = resultSet.getString(i);
        return str;
    }

    @Override
    public String getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String str = callableStatement.getString(i);
        return str;
    }

    private String handleField(String str, String fieldName) {
        if (URL_FIELD != null && URL_FIELD.length > 0
                && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(fieldName)) {
            for(String field : URL_FIELD) {
                if (fieldName.equals(field)) {
                    return urlPrefix + str;
                }
            }
        }
        return null;
    }

}
