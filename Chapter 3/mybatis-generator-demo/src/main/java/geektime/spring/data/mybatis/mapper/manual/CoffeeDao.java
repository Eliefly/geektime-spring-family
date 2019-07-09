package geektime.spring.data.mybatis.mapper.manual;

import geektime.spring.data.mybatis.domain.Coffee;
import geektime.spring.data.mybatis.mapper.generated.CoffeeMapper;
import org.apache.ibatis.annotations.Param;

/**
 * CoffeeDao
 *
 * @author eliefly
 * @date 2019-07-07
 */
public interface CoffeeDao extends CoffeeMapper {
    Coffee findByName(@Param("name") String name);
}
