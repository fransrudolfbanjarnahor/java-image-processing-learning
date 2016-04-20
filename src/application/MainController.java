package application;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class MainController implements Initializable {
	BufferedImage bImage;
	BufferedImage pImage;
	@FXML
	ImageView img;
	BufferedImage im;

	@FXML
	Slider threshold;

	@FXML
	Label tSize;

	Matriks m;
	int[][] bdata;
	public void browseImage(ActionEvent event) throws IOException {
		FileChooser fc = new FileChooser();
		FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("JPG Files", "*.jpg");
		FileChooser.ExtensionFilter pngFilter = new FileChooser.ExtensionFilter("PNG Files", "*.png");
		fc.getExtensionFilters().addAll(jpgFilter, pngFilter);

		File selectedFile = fc.showOpenDialog(null);
		if (selectedFile != null) {
			bImage = ImageIO.read(selectedFile);
			img.setImage(SwingFXUtils.toFXImage(bImage, null));
			bdata = new int[bImage.getWidth()][bImage.getHeight()];
		}
	}

	public void applyImage() {
		System.out.println("apply images");
		bImage = im;
	}

	public void grayScale(ActionEvent event) throws IOException {
		im = new BufferedImage(bImage.getWidth(), bImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for (int i = 0; i < bImage.getWidth(); i++) {
			for (int j = 0; j < bImage.getHeight(); j++) {
				Color c = new Color(bImage.getRGB(i, j));
				int red = (int) (c.getRed() * 0.299);
				int green = (int) (c.getGreen() * 0.587);
				int blue = (int) (c.getBlue() * 0.114);

				Color newColor = new Color(red + green + blue, red + green + blue, red + green + blue);
				im.setRGB(i, j, newColor.getRGB());
				// System.out.print(red + "." + green + "." + blue + "." + alpha
				// + " ");
			}
		}
		img.setImage(SwingFXUtils.toFXImage(im, null));
	}

	public void citraBiner(ActionEvent event) throws IOException {
		im = new BufferedImage(bImage.getWidth(), bImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
		int biner = 0;
		tSize.setText(String.valueOf(threshold.getValue()));
		for (int i = 0; i < bImage.getWidth(); i++) {
			for (int j = 0; j < bImage.getHeight(); j++) {
				Color c = new Color(bImage.getRGB(i, j));
				int r = (int) (c.getRed());
				int g = (int) (c.getGreen());
				int b = (int) (c.getBlue());
				// System.out.println("" + (((r + g + b) / 3) / 2));
				if ((((r + g + b) / 3) / 2) < threshold.getValue()) {
					biner = 0;
				} else {
					biner = 1;
				}
				bdata[i][j] = biner;
				Color newColor = new Color((biner * 255), (biner * 255), (biner * 255));
				im.setRGB(i, j, newColor.getRGB());
			}
		}
		img.setImage(SwingFXUtils.toFXImage(im, null));
	}

	public void negative(ActionEvent event) throws IOException {
		im = new BufferedImage(bImage.getWidth(), bImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for (int i = 0; i < bImage.getWidth(); i++) {
			for (int j = 0; j < bImage.getHeight(); j++) {
				Color c = new Color(bImage.getRGB(i, j));
				int r = (int) (c.getRed());
				int g = (int) (c.getGreen());
				int b = (int) (c.getBlue());

				Color newColor = new Color(255 - r, 255 - g, 255 - b);
				im.setRGB(i, j, newColor.getRGB());
			}
		}
		img.setImage(SwingFXUtils.toFXImage(im, null));
	}

	public void red(ActionEvent event) throws IOException {
		im = new BufferedImage(bImage.getWidth(), bImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for (int i = 0; i < bImage.getWidth(); i++) {
			for (int j = 0; j < bImage.getHeight(); j++) {
				Color c = new Color(bImage.getRGB(i, j));
				int r = (int) (c.getRed());

				Color newColor = new Color(r, 0, 0);
				im.setRGB(i, j, newColor.getRGB());
			}
		}
		img.setImage(SwingFXUtils.toFXImage(im, null));
	}

	public void green(ActionEvent event) throws IOException {
		im = new BufferedImage(bImage.getWidth(), bImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for (int i = 0; i < bImage.getWidth(); i++) {
			for (int j = 0; j < bImage.getHeight(); j++) {
				Color c = new Color(bImage.getRGB(i, j));
				int g = (int) (c.getGreen());

				Color newColor = new Color(0, g, 0);
				im.setRGB(i, j, newColor.getRGB());
			}
		}
		img.setImage(SwingFXUtils.toFXImage(im, null));
	}
	
	public void flipHorizontal(ActionEvent event) throws IOException {
		im = new BufferedImage(bImage.getWidth(), bImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for (int i = 0; i < bImage.getWidth(); i++) {
			for (int j = 0; j < bImage.getHeight(); j++) {
				im.setRGB(bImage.getWidth()-i-1, j, bImage.getRGB(i, j));
			}
		}
		img.setImage(SwingFXUtils.toFXImage(im, null));
	}
	
	public void flipVertical(ActionEvent event) throws IOException {
		im = new BufferedImage(bImage.getWidth(), bImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for (int i = 0; i < bImage.getWidth(); i++) {
			for (int j = 0; j < bImage.getHeight(); j++) {
				im.setRGB(i, bImage.getHeight()-j-1, bImage.getRGB(i, j));
			}
		}
		img.setImage(SwingFXUtils.toFXImage(im, null));
	}
	
	public void flipHorizontalVertical(ActionEvent event) throws IOException {
		im = new BufferedImage(bImage.getWidth(), bImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for (int i = 0; i < bImage.getWidth(); i++) {
			for (int j = 0; j < bImage.getHeight(); j++) {
				im.setRGB(bImage.getWidth()-i-1, bImage.getHeight()-j-1, bImage.getRGB(i, j));
			}
		}
		img.setImage(SwingFXUtils.toFXImage(im, null));
	}

	public void blue(ActionEvent event) throws IOException {
		BufferedImage im = new BufferedImage(bImage.getWidth(), bImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for (int i = 0; i < bImage.getWidth(); i++) {
			for (int j = 0; j < bImage.getHeight(); j++) {
				Color c = new Color(bImage.getRGB(i, j));
				int b = (int) (c.getBlue());
				Color newColor = new Color(0, 0, b);
				im.setRGB(i, j, newColor.getRGB());
			}
		}
		img.setImage(SwingFXUtils.toFXImage(im, null));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		threshold.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				tSize.setText(String.valueOf(newValue.intValue()));
				try {
					citraBiner(null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public void debugMatrix() throws FileNotFoundException {
		m = new Matriks(bdata);
		m.writeToFile();
	}
}
