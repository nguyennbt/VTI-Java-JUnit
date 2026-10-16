package vn.vti;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Thứ tự 6 cấp độ Log chuẩn trong Log4j 2 (từ cao đến thấp):
FATAL: Lỗi đặc biệt nghiêm trọng khiến toàn bộ hệ thống hoặc ứng dụng bị sập, dừng hoạt động.

ERROR: Lỗi xảy ra trong quá trình thực thi một chức năng (thường đặt trong khối catch khi bắt exception), chức năng đó thất bại nhưng toàn bộ ứng dụng vẫn chạy tiếp.

WARN: Cảnh báo rủi ro tiềm ẩn (ví dụ: tài nguyên sắp đầy, phương thức sắp bị deprecated, cấu hình chưa chuẩn) dù ứng dụng vẫn chưa bị lỗi.

INFO: Thông tin trạng thái hoạt động bình thường của hệ thống (ví dụ: Server started at port 8080, User logged in successfully).

DEBUG: Thông tin chi tiết dành cho lập trình viên để tìm lỗi trong lúc phát triển phần mềm (biến đầu vào, kết quả truy vấn query,...).

TRACE: Mức ghi log chi tiết nhất, dùng để trace vết từng dòng lệnh, luồng chạy hoặc vòng lặp.
 */

public class log4J {
    private  static  final  Logger log = LogManager.getLogger(log4J.class);
    public static void main(String[] args) {
        int result = new App().add(0, 8);
        System.out.println("Result is: " + result);
        log.fatal("Result is: {}", result);
        log.error("Result is: {}", result);
        log.warn("Result is: {}", result);   // Cảnh báo
        log.info("Result is: {}", result);   // Thông tin bình thường
        log.debug("Result is: {}", result);  // Thông tin debug khi lập trình
        log.trace("Result is: {}", result);  // Chi tiết từng bước chạy (mức thấp nhất)
    }
}
