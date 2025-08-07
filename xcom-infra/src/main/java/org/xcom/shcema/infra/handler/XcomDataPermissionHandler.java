package org.xcom.shcema.infra.handler;

import com.baomidou.mybatisplus.extension.plugins.handler.DataPermissionHandler;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.LikeExpression;
import net.sf.jsqlparser.schema.Column;

import java.util.ArrayList;
import java.util.List;

/**
 * Xcom 数据权限处理
 *
 * @author xcom
 * @date 2024/8/9
 */

public class XcomDataPermissionHandler implements DataPermissionHandler {
    @Override
    public Expression getSqlSegment(Expression where, String mappedStatementId) {


        // 假设 column 是你要应用 LIKE 条件的列
        Column column = new Column("your_column_name");

        // 假设 searchValue 是你要搜索的值
        String searchValue = "%searchValue%";

        // 创建 LIKE 条件
        // 左LIKE (searchValue 在左边)
        String leftLikeValue = "searchValue%";
        LikeExpression leftLike = new LikeExpression();
        leftLike.setLeftExpression(column);
        leftLike.setRightExpression(new StringValue(leftLikeValue));

        // 右LIKE (searchValue 在右边)
        String rightLikeValue = "%searchValue";
        LikeExpression rightLike = new LikeExpression();
        rightLike.setLeftExpression(column);
        rightLike.setRightExpression(new StringValue(rightLikeValue));

        // 全LIKE (searchValue 同时在左右两边)
        LikeExpression fullLike = new LikeExpression();
        fullLike.setLeftExpression(column);
        fullLike.setRightExpression(new StringValue(searchValue));

        // 根据需要选择一个 LIKE 条件
        // 选择左LIKE条件
        Expression likeExpression = leftLike;

        // 如果原始的where不为null，则需要将 LIKE 条件与原始条件用AND连接起来
        if (where != null) {
            // and
            where = new AndExpression(where, likeExpression);
        } else {
            where = likeExpression;
        }

        // in
        InExpression inExpression = new InExpression();
        column = new Column("columnName");
        List<Expression> values = new ArrayList<>();
        values.add(new StringValue("value1"));
        values.add(new StringValue("value2"));
        inExpression.setLeftExpression(column);
        inExpression.setRightItemsList(new ExpressionList(values));


// or
        where = new OrExpression(where, inExpression);


        return where;
    }
}
