package geektime.spring.data.mybatis.mapper.generated;

import geektime.spring.data.mybatis.domain.Coffee;
import geektime.spring.data.mybatis.domain.CoffeeExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

public interface CoffeeMapper {
    long countByExample(CoffeeExample example);

    int deleteByExample(CoffeeExample example);

    @Delete({
        "delete from T_COFFEE",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into T_COFFEE (NAME, PRICE, ",
        "CREATE_TIME, UPDATE_TIME)",
        "values (#{name,jdbcType=VARCHAR}, #{price,jdbcType=BIGINT,typeHandler=geektime.spring.data.mybatis.handler.MoneyTypeHandler}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="CALL IDENTITY()", keyProperty="id", before=false, resultType=Long.class)
    int insert(Coffee record);

    int insertSelective(Coffee record);

    List<Coffee> selectByExampleWithRowbounds(CoffeeExample example, RowBounds rowBounds);

    List<Coffee> selectByExample(CoffeeExample example);

    @Select({
        "select",
        "ID, NAME, PRICE, CREATE_TIME, UPDATE_TIME",
        "from T_COFFEE",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("geektime.spring.data.mybatis.mapper.generated.CoffeeMapper.BaseResultMap")
    Coffee selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Coffee record, @Param("example") CoffeeExample example);

    int updateByExample(@Param("record") Coffee record, @Param("example") CoffeeExample example);

    int updateByPrimaryKeySelective(Coffee record);

    @Update({
        "update T_COFFEE",
        "set NAME = #{name,jdbcType=VARCHAR},",
          "PRICE = #{price,jdbcType=BIGINT,typeHandler=geektime.spring.data.mybatis.handler.MoneyTypeHandler},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP}",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Coffee record);
}