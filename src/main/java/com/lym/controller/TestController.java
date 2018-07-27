package com.lym.controller;

import com.lym.Enum.SexEnum;
import com.lym.common.JsonData;
import com.lym.dto.TestExcelStuDTO;
import com.lym.dto.request.TestValidateRequestDTO;
import com.lym.exception.PermissionException;
import com.lym.model.SysUser;
import com.lym.util.BeanValidator;
import com.lym.util.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/7/5 14:43
 * @Description: 测试启动
 */
@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {

    @RequestMapping("/hello.json")
    @ResponseBody
    public JsonData hello() {
        log.info("hello");
//        throw new RuntimeException("test exception");
        throw new PermissionException("test exception");
//        return JsonData.sucess("hello,permission");
    }

    @RequestMapping("/validate.json")
    @ResponseBody
    public JsonData validate(@RequestBody TestValidateRequestDTO testValidateRequestDTO) {
        log.info("validate");
        try {
            Map<String, String> map = BeanValidator.validateObject(testValidateRequestDTO);
            if (map != null && map.entrySet().size() > 0) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    log.info("{}-->{}", entry.getKey(), entry.getValue());
                }
            }
        } catch (Exception e) {

        }
        return JsonData.sucess("test validate");
    }

    /**
     * 导出excel
     *
     * @param request
     * @param response
     */
    @RequestMapping("/export.page")
    @ResponseBody
    public void export(HttpServletRequest request, HttpServletResponse response) {

        //制造学生假数据
        List<TestExcelStuDTO> list = new ArrayList<>();
        TestExcelStuDTO stuDTO = null;
        for (int i = 0; i < 5; i++) {
            stuDTO = new TestExcelStuDTO();
            stuDTO.setName("李彦明" + i);
            stuDTO.setAge(18 + i);
            stuDTO.setSchool("测试学校" + i);
            stuDTO.setGrade("高三" + i + 1 + "班");
            stuDTO.setSex(SexEnum.getValueByKey(i % 2 + 1));
            list.add(stuDTO);
        }

        //Excel标题
        String[] title = {"名称", "性别", "年纪", "学校", "班级"};

        //sheet名
        String sheetName = "学生信息表";

        //Excel文件名
        String fileName = "学生信息表" + System.currentTimeMillis() + ".xls";

        String[][] content = new String[title.length][list.size()];

        for (int i = 0; i < list.size(); i++) {
            content[i] = new String[title.length];
            TestExcelStuDTO obj = list.get(i);
            content[i][0] = obj.getName();
            content[i][1] = obj.getSex();
            content[i][2] = obj.getAge() + "";
            content[i][3] = obj.getSchool();
            content[i][4] = obj.getGrade();
        }

        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);

        //响应到客户端
        try {
            this.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {

        }
    }

    /**
     * 发送响应流方法
     *
     * @param response
     * @param fileName
     */
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(), "ISO-8859-1");
            } catch (Exception e) {

            }
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-chche");
        } catch (Exception e) {

        }
    }

    /**
     * 测试时间
     *
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
//        Format f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2018-02-20 20:27:00");
//        Date today = new Date();
//        System.out.println("今天是:" + f.format(date));
//
//        Calendar c = Calendar.getInstance();
//        c.setTime(date);
////        c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
//        c.add(Calendar.HOUR_OF_DAY, 240);
//        Date tomorrow = c.getTime();
//        System.out.println("下一个小时是:" + f.format(tomorrow));
        SysUser user = null;
        List<SysUser> users = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            user = new SysUser();
            user.setUsername("test01");
            users.add(user);
        }
        users.forEach(new Consumer<SysUser>() {
            @Override
            public void accept(SysUser sysUser) {
                sysUser.setUsername("lym");
            }
        });

    }
}
