package com.watabou.pixeldungeon.windows;

import android.util.Log;

import com.nyrds.pixeldungeon.items.accessories.Accessory;
import com.nyrds.pixeldungeon.ml.R;
import com.nyrds.pixeldungeon.support.Iap;
import com.watabou.noosa.Game;
import com.watabou.noosa.Image;
import com.watabou.noosa.Text;
import com.watabou.noosa.ui.Component;
import com.watabou.pixeldungeon.scenes.GameScene;
import com.watabou.pixeldungeon.scenes.PixelScene;
import com.watabou.pixeldungeon.ui.ScrollPane;
import com.watabou.pixeldungeon.ui.SystemRedButton;
import com.watabou.pixeldungeon.ui.TextButton;
import com.watabou.pixeldungeon.ui.Window;

import java.util.List;

public class WndHats extends Window {

	private static final int WIDTH = 120;
	private static final int HEIGHT = 180;
	private static final int MARGIN = 2;
	private static final int BUTTON_HEIGHT = 16;

	public WndHats() {

		int yPos = 0;

		Text tfTitle = PixelScene.createMultiline(Game.getVar(R.string.WndHats_Title), 10);
		tfTitle.hardlight(TITLE_COLOR);
		tfTitle.maxWidth(WIDTH - MARGIN * 2);
		tfTitle.measure();
		tfTitle.x = WIDTH/2 - tfTitle.width()/2;
		tfTitle.y = MARGIN;

		yPos += tfTitle.height() + MARGIN;
		add(tfTitle);

		List<String> hats = Accessory.getAccessoriesList();

		Component content = new Component();

		for (final String item: hats) {
			final String price = "9999";// Iap.getSkuPrice(item);
			if(price!=null) {

				Image hat = Accessory.getByName(item).getImage();
				hat.setPos(0,yPos);
				content.add(hat);

				Text info = PixelScene.createMultiline(Accessory.getByName(item).desc(), 9 );

				info.hardlight(0xFFFFFF);
				info.x = hat.x + hat.width();
				info.y = hat.y;
				info.maxWidth(WIDTH - (int)hat.width());
				info.measure();

			    content.add(info);

				TextButton rb = new SystemRedButton(Accessory.getByName(item).name() + " " + price) {
					@Override
					protected void onClick() {
						super.onClick();
						GameScene.show( new WndHatInfo(item, price) );
						//Iap.doPurchase(item);
					}
				};

				rb.setRect(hat.width(),info.y + info.height() + MARGIN, WIDTH / 2, BUTTON_HEIGHT );

			    content.add(rb);
				yPos += rb.height() + info.height() + MARGIN * 8;
			}
			else{
				Text info = PixelScene.createMultiline( Game.getVar(R.string.WndHats_NoConnectionMsg), 10 );

				info.hardlight(0xFFFFFF);
				info.x = MARGIN;
				info.y = yPos;
				info.maxWidth(WIDTH - MARGIN * 2);
				info.measure();

				content.add(info);
				yPos = yPos * 5;
				break;
			}
		}

		int h = Math.min(HEIGHT - MARGIN, yPos);

		resize( WIDTH,  h);

		content.setSize(WIDTH, yPos);
		ScrollPane list = new ScrollPane(content);
		list.dontCatchTouch();

		add(list);

		list.setRect(0, tfTitle.y + tfTitle.height() + MARGIN, WIDTH, HEIGHT - tfTitle.height() - MARGIN * 2);

	}
}