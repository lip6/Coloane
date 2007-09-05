package fr.lip6.move.coloane.api.cami.input.dialog;

public final class DialogCreation {

	public enum DialogType {
		standard(1), warning(2), error(3), interactive(4); // deprecated

		private int value;

		private DialogType(int value) {
			this.value = value;
		}

		public int getInt() {
			return this.value;
		}
	}

	public static DialogType DialogType(int i) {
		DialogType toReturn = DialogType.standard;
		for (DialogType s : DialogType.values()) {
			if (s.value == i) {
				toReturn = s;
			}
		}
		return toReturn;
	}

	public enum ButtonsType {
		noButtons(1), okButton(2), okAndCancelButtons(3);

		private int value;

		private ButtonsType(int value) {
			this.value = value;
		}

		public int getInt() {
			return this.value;
		}
	}

	public static ButtonsType ButtonsType(int i) {
		ButtonsType toReturn = ButtonsType.noButtons;
		for (ButtonsType s : ButtonsType.values()) {
			if (s.value == i) {
				toReturn = s;
			}
		}
		return toReturn;
	}

	public enum InputType {
		inputAuthorized(1), displayOnly(2), inputWithAbortAuthorized(5);

		private int value;

		private InputType(int value) {
			this.value = value;
		}

		public int getInt() {
			return this.value;
		}
	}

	public static InputType InputType(int i) {
		InputType toReturn = InputType.inputAuthorized;
		for (InputType s : InputType.values()) {
			if (s.value == i) {
				toReturn = s;
			}
		}
		return toReturn;
	}

	public enum LineType {
		singleLine(2), multipleLineSingleSelection(1), multipleLineMutlipleSelection(5);

		private int value;

		private LineType(int value) {
			this.value = value;
		}

		public int getInt() {
			return this.value;
		}
	}

	public static LineType LineType(int i) {
		LineType toReturn = LineType.singleLine;
		for (LineType s : LineType.values()) {
			if (s.value == i) {
				toReturn = s;
			}
		}
		return toReturn;
	}

	private int dialogId;
	private DialogType dialogType;
	private ButtonsType buttonsTypes;
	private String windowTitle;
	private String help;
	private String titleOrMessage;
	private InputType inputType;
	private LineType lineType;
	private String defaultValue;

	public DialogCreation(int dialogId,
							DialogType dialogType,
							ButtonsType buttonsTypes,
							String windowTitle,
							String help,
							String titleOrMessage,
							InputType inputType,
							LineType lineType,
							String defaultValue) {
		super();
		this.dialogId = dialogId;
		this.dialogType = dialogType;
		this.buttonsTypes = buttonsTypes;
		this.windowTitle = windowTitle;
		this.help = help;
		this.titleOrMessage = titleOrMessage;
		this.inputType = inputType;
		this.lineType = lineType;
		this.defaultValue = defaultValue;
	}

	public int getDialogId() {
		return this.dialogId;
	}

	public DialogType getDialogType() {
		return this.dialogType;
	}

	public ButtonsType getButtonsTypes() {
		return this.buttonsTypes;
	}

	public String getWindowTitle() {
		return this.windowTitle;
	}

	public String getHelp() {
		return this.help;
	}

	public String getTitleOrMessage() {
		return this.titleOrMessage;
	}

	public LineType getLineType() {
		return lineType;
	}

	public void setLineType(LineType lineType) {
		this.lineType = lineType;
	}

	public InputType getInputType() {
		return this.inputType;
	}

	public String getDefaultValue() {
		return this.defaultValue;
	}
}
