package com.hascode.tutorial.samples;

import java.io.File;
import java.net.URL;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.Map;
import org.apache.pivot.collections.Sequence;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Alert;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.DesktopApplicationContext;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.FileBrowserSheet;
import org.apache.pivot.wtk.ListView;
import org.apache.pivot.wtk.MessageType;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.Sheet;
import org.apache.pivot.wtk.SheetCloseListener;
import org.apache.pivot.wtk.Window;

public class Sample2 {
	public static void main(final String[] args) {
		DesktopApplicationContext.main(MyApp.class, args);
	}

	public static class MyApp extends Window implements Application, Bindable {
		private Window window = null;

		@BXML
		private PushButton btOpenFileDialog;

		private final FileBrowserSheet fileBrowser;

		public MyApp() {
			fileBrowser = new FileBrowserSheet();
		}

		@Override
		public void initialize(final Map<String, Object> namespace,
				final URL location, final Resources resources) {
			fileBrowser.setMode(FileBrowserSheet.Mode.SAVE_AS);
			btOpenFileDialog.getButtonPressListeners().add(
					fileDialogDisplayListener);
		}

		@Override
		public void startup(final Display display,
				final Map<String, String> properties) throws Exception {
			BXMLSerializer bxmlSerializer = new BXMLSerializer();
			window = (Window) bxmlSerializer.readObject(Sample2.class,
					"/filemanager.bxml");
			window.open(display);
		}

		private final ButtonPressListener fileDialogDisplayListener = new ButtonPressListener() {
			@Override
			public void buttonPressed(final Button button) {
				fileBrowser.open(MyApp.this, new SheetCloseListener() {
					@Override
					public void sheetClosed(final Sheet sheet) {
						if (sheet.getResult()) {
							Sequence<File> selectedFiles = fileBrowser
									.getSelectedFiles();

							ListView listView = new ListView();
							listView.setListData(new ArrayList<File>(
									selectedFiles));
							listView.setSelectMode(ListView.SelectMode.NONE);
							listView.getStyles().put("backgroundColor", null);

							Alert.alert(MessageType.INFO,
									"Files selected for upload:", listView,
									MyApp.this);
						} else {
							Alert.alert(MessageType.INFO,
									"No files selected for upload.", MyApp.this);
						}
					}
				});
			}
		};

		@Override
		public boolean shutdown(final boolean optional) throws Exception {
			this.close();
			return false;
		}

		@Override
		public void suspend() throws Exception {
		}

		@Override
		public void resume() throws Exception {
		}
	}
}
