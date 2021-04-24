package com.unclezs.novel.app.framework.components;

import com.unclezs.novel.app.framework.components.icon.IconButton;
import com.unclezs.novel.app.framework.util.NodeHelper;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import lombok.Getter;
import lombok.Setter;

/**
 * 带图标的输入框
 *
 * @author blog.unclezs.com
 * @date 2021/4/22 12:39
 */
public class InputBox extends HBox {

  public static final EventType<ActionClickedEvent> ON_ACTION_CLICKED = new EventType<>(InputEvent.ANY, "ON_ICON_CLICKED");
  public static final String DEFAULT_STYLE_CLASS = "input-box";
  @Getter
  private final TextField input;
  @Getter
  private final IconButton action;
  private String icon = "edit";
  /**
   * 提示文字
   */
  @Getter
  private String prompt;
  /**
   * 搜索事件监听
   */
  @Getter
  @Setter
  private EventHandler<? super ActionClickedEvent> onIconClicked = e -> {
  };

  public InputBox() {
    NodeHelper.addClass(this, DEFAULT_STYLE_CLASS);
    this.input = new TextField();
    HBox.setHgrow(input, Priority.ALWAYS);
    action = new IconButton(icon, null);
    this.getChildren().setAll(input, action);
    this.input.setOnKeyPressed(event -> {
      if (event.getCode() == KeyCode.ENTER) {
        onIconClicked.handle(new ActionClickedEvent(input.getText()));
      }
    });
    action.setOnMouseClicked(event -> onIconClicked.handle(new ActionClickedEvent(input.getText())));
  }

  /**
   * 设置action按钮图标
   *
   * @param icon 图标
   */
  public void setIcon(String icon) {
    this.icon = icon;
    action.setIcon(icon);
  }

  /**
   * 获取焦点
   */
  public void focus() {
    input.requestFocus();
  }


  public void setPrompt(String prompt) {
    this.prompt = prompt;
    this.input.setPromptText(prompt);
  }

  /**
   * 搜索事件
   */
  public static class ActionClickedEvent extends InputEvent {

    @Getter
    private final String input;

    public ActionClickedEvent(String input) {
      super(ON_ACTION_CLICKED);
      this.input = input;
    }
  }
}
