package com.company.demo.beans

import grails.util.Holders
import javax.annotation.PostConstruct

import com.company.demo.AnagraphicDomain
import com.company.demo.AnagraphicService

import grails.plugins.primefaces.GrailsService
import grails.plugins.primefaces.MessageSourceBean

import javax.faces.bean.ManagedBean
import javax.faces.bean.ManagedProperty
import javax.faces.bean.ViewScoped
import javax.faces.context.FacesContext

import org.primefaces.event.SelectEvent
import org.primefaces.model.LazyDataModel

import org.springframework.core.SpringVersion
import org.springframework.validation.FieldError
import javax.persistence.EntityManagerFactory

import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.RequestAttributes


import javax.faces.event.ActionEvent

import groovy.util.logging.Slf4j
import grails.util.Holders

/**
* The is example bean for Anagraphic domain class.
* source file https://github.com/andreaminnucci/primefaces
* Modify &amp; Improve: Prominic -Bing Li
* Data: 2019-04-21
*/
 
@ManagedBean(name = "anagraphicMB")
@ViewScoped
@Slf4j
public class AnagraphicManagedBean implements Serializable {

     def servletContext =Holders.getServletContext()
        
    @PostConstruct
    public void init() {
        log.debug "---> @PostConstruct --- " + new java.util.Date().toString()
        anagraphics = new LazyAnagraphicDataModel()
    }
    
    @ManagedProperty(value = "#{message}")
    MessageSourceBean message
    
    //@GrailsService(name = "anagraphicService")
    def anagraphicService=Holders.grailsApplication.mainContext.getBean 'anagraphicService'
    //def anagraphicDomainService=Holders.grailsApplication.mainContext.getBean 'anagraphicDomainService'
    AnagraphicDomain anagraphic
    LazyDataModel<AnagraphicDomain> anagraphics

    private String deleteId;

    private String theme;

    public String getTheme(){
        return this.theme
    }

    public void setTheme(String theme){
        println "set theme:"+theme
        servletContext.setAttribute("primeface_theme",theme)
        this.theme=theme
    }

    public String getDeleteId(){
        return this.deleteId
    }

    public void setDeleteId(String id){
        this.deleteId=id
    }

    public void save() {


       // println "save:"+anagraphic.class.getSimpleName()
        //log.info(anagraphic)        
        boolean updated = (anagraphic.id != null)
        List<FieldError> errors =anagraphicService.save(anagraphic)
        if (errors == null) {
            if (updated == true) {
                message.infoPF("com.company.demo.Anagraphic.updated.message", null)
            } else {
                message.infoPF("com.company.demo.Anagraphic.created.message", null)
            }
            reset()
        } else {
            for (FieldError error : errors) {
                message.errorMessagePF("", message.getErrorMessage(error))
            }
        }
    }
    
    public void reset() {
        log.info "reset"
        anagraphic = new AnagraphicDomain()
    }
    
    public void delete(ActionEvent event) {

      def id  = (String)event.getComponent().getAttributes().get("deleteid");
      // params.get("deleteid");//RequestContextHolder.getRequestAttributes().getParams().get("id")
        if(id!=null){
        log.debug "id:"+id
        Long longId=Long.parseLong(id)
        getAnagraphicService().delete(longId)
        message.infoPF("com.company.demo.Anagraphic.deleted.message", null)
        }
      
    }

    public void onRowSelect(SelectEvent event) {
        Long id =((AnagraphicDomain) event.getObject()).getId()
        log.debug("id = " + id)
        anagraphic = anagraphicService.get(id)
    }
	

}

