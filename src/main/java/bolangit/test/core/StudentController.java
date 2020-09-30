package bolangit.test.core;


import bolangit.test.model.Student;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
public class StudentController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @GetMapping(path = "/list")
    public String list(){
        List<Student> list = jdbcTemplate.query("select * from student", new Object[]{}, new BeanPropertyRowMapper<>(Student.class));
        return JSON.toJSONString(list);
    }
    @PostMapping(path= "/add", consumes = "application/json", produces = "application/json")
    public String add( @RequestBody Student stu){
        int res = jdbcTemplate.update("insert into student(id, name,sex,age,clas ) values(?, ?, ?, ?, ?)", stu.getId(), stu.getName(),stu.getSex(),stu.getAge(),stu.getClas());
        return String.valueOf(res);
    }

    @PutMapping(path= "/put", consumes = "application/json", produces = "application/json")
    public String put( @RequestBody Student stu){
        int res = jdbcTemplate.update("update student set name = ? where id = ?",stu.getName(), stu.getId());
        return String.valueOf(res);
    }

    @DeleteMapping(path= "/del", consumes = "application/json", produces = "application/json")
    public String del( @RequestBody Student stu){
        int res = jdbcTemplate.update("delete from student where id = ?", stu.getId());
        return String.valueOf(res);
    }


}