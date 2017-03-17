package com.volvo.sits3.wechat.api.actions;

import com.volvo.sits3.wechat.api.actions.forms.EchoForm;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class EchoAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws Exception {
        EchoForm echoForm = (EchoForm) form;

        response.getWriter().write(echoForm.getEchostr());

        return null;
    }
}
