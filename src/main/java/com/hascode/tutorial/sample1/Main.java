package com.hascode.tutorial.sample1;

import org.apache.pivot.collections.Map;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.DesktopApplicationContext;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.FileBrowserSheet;
import org.apache.pivot.wtk.Form;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.Window;

public class Main {
	public static void main(final String[] args) {
		DesktopApplicationContext.main(MyApp.class, new String[] {});
	}

	public static class MyApp extends Window implements Application {
		private final Form form = new Form();
		private final Form.Section section = new Form.Section();
		private final Label label = new Label("Select file to upload...");
		private final PushButton btOpenFileDialog = new PushButton();
		private final FileBrowserSheet fileBrowser = new FileBrowserSheet();

		public MyApp() {
			compose();
		}

		private void compose() {
			this.setTitle("hasCode.com - Apache Pivot Example 1 - Programmatic construction");
			this.setMaximized(true);
			section.add(label);
			btOpenFileDialog.setButtonData("Select File..");
			section.add(btOpenFileDialog);
			form.getSections().add(section);
			this.add(form);
		}

		public void startup(final Display display,
				final Map<String, String> properties) throws Exception {
			this.open(display);
		}

		public boolean shutdown(final boolean optional) throws Exception {
			this.close();
			return false;
		}

		public void suspend() throws Exception {
		}

		public void resume() throws Exception {
		}

	}
}
