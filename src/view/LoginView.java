package view;

import com.pubnub.api.PubNubException;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "log in";
    final JTextField usernameInputField = new JTextField(15);
    final JPasswordField passwordInputField = new JPasswordField(15);
    final JButton logIn;
    final JButton signUp;
    private final LoginViewModel loginViewModel;
    private final JLabel usernameErrorField = new JLabel();
    private final JLabel passwordErrorField = new JLabel();
    private final LoginController loginController;

    public LoginView(LoginViewModel loginViewModel, LoginController controller) {

        this.loginController = controller;
        this.loginViewModel = loginViewModel;
        this.loginViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Login");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("Enter Username"), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Enter Password"), passwordInputField);

        JPanel buttons = new JPanel();
        logIn = new JButton(LoginViewModel.LOGIN_BUTTON_LABEL);
        buttons.add(logIn);
        signUp = new JButton(LoginViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(signUp);

        logIn.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logIn)) {
                            LoginState currentState = loginViewModel.getState();
                            currentState.setUsername(usernameInputField.getText());
                            currentState.setPassword(passwordInputField.getText());
                            loginViewModel.setState(currentState);
                            try {
                                usernameInputField.setText("");
                                passwordInputField.setText("");
                                loginController.execute(
                                        currentState.getUsername(),
                                        currentState.getPassword()
                                );
                            } catch (PubNubException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
        );

        signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usernameInputField.setText("");
                passwordInputField.setText("");
                loginController.jump();
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(usernameErrorField);
        this.add(passwordInfo);
        this.add(passwordErrorField);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoginState state = (LoginState) evt.getNewValue();
        setFields(state);
        if(evt.getSource().equals(loginViewModel)){
            if (state.getUsernameError() != null) {
                JOptionPane.showMessageDialog(this, state.getUsernameError());
            } else if (state.getPasswordError() != null) {
                JOptionPane.showMessageDialog(this, state.getPasswordError());
            }
        }
    }

    private void setFields(LoginState state) {
        usernameInputField.setText(state.getUsername());
    }

}