package br.com.k19.registerBugs.controller;

import br.com.k19.registerBugs.model.entity.User;
import br.com.k19.registerBugs.model.repository.UserRepository;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@Named
@SessionScoped
public class AuthenticatorMB implements Serializable {

    @Inject
    private UserRepository userRepository;

    private User user = new User();

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (this.userRepository.validateLogin(this.user)) {
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            session.setAttribute("user", this.user.getUsername());

            return "home?faces-redirect=true";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "user and/or password invalid", null);
            context.addMessage(null, message);

            return "login";
        }
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.getAttribute("user");

        return "login";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}