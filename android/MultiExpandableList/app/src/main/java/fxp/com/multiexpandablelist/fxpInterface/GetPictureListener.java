package fxp.com.multiexpandablelist.fxpInterface;

/**
 * 获取照片接口
 * <p>
 * Created by fxp on 2018/3/4.
 */

public interface GetPictureListener {

    // 拍照
    void takePicture(int groupPosition, int childPosition);

    // 从相册获取照片F
    void selectPicture(int groupPosition, int childPosition);

}
