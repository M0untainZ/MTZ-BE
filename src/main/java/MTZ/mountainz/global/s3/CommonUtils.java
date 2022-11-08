package MTZ.mountainz.global.s3;

import java.util.ArrayList;
import java.util.UUID;

public class CommonUtils {
    public static String buildFileName(String originalFileName) {
        // 이미지파일명 중복 방지
        return UUID.randomUUID().toString().concat(getFileExtension(originalFileName));
    }

    // 파일 유효성 검사
    private static String getFileExtension(String fileName) {
        if (fileName.length() == 0) {
            throw new IllegalArgumentException();
        }
        ArrayList<String> fileValidate = new ArrayList<>();
        fileValidate.add(".jpg");
        fileValidate.add(".jpeg");
        fileValidate.add(".png");
        fileValidate.add(".JPG");
        fileValidate.add(".JPEG");
        fileValidate.add(".PNG");
        String idxFileName = fileName.substring(fileName.lastIndexOf("."));
        if (!fileValidate.contains(idxFileName)) {
            throw new IllegalArgumentException();
        }
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
