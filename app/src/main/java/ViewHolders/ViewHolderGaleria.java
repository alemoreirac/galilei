package ViewHolders;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Pefoce on 21/06/2017.
 */


public class ViewHolderGaleria {
    TextView imageTitle;
    ImageView image;

    public TextView getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(TextView imageTitle) {
        this.imageTitle = imageTitle;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }
}
