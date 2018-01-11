package net.rokyinfo.insurance.config;

import net.rokyinfo.insurance.util.JacksonUtil;
import org.apache.http.util.TextUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@MappedTypes(List.class)
@MappedJdbcTypes({JdbcType.VARCHAR})
public class ListTypeHandler extends BaseTypeHandler<List<String>> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<String> strings, JdbcType jdbcType) throws SQLException {
        if (strings != null && strings.size() > 0) {
            preparedStatement.setString(i, JacksonUtil.toJSon(strings));
        }
    }

    @Override
    public List<String> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String str = resultSet.getString(s);
        return handleField(str);
    }

    @Override
    public List<String> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String str = resultSet.getString(i);
        return handleField(str);
    }

    @Override
    public List<String> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String str = callableStatement.getString(i);
        return handleField(str);
    }

    private List<String> handleField(String str) {
        if (!TextUtils.isEmpty(str)) {
            List<String> strings = JacksonUtil.readValue(str, List.class);
            List<String> list = new ArrayList<>();
            strings.forEach( s -> {
                list.add(UrlTypeHandler.urlPrefix + s);
            });
            return list;
        }
        return null;
    }

}