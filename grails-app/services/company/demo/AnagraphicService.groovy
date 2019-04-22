package com.company.demo

import grails.gorm.transactions.*
import org.springframework.validation.FieldError

@Transactional
class AnagraphicService {
    static transactional = false
    AnagraphicDomain get(Long id) {
        AnagraphicDomain.withTransaction {
            AnagraphicDomain.get(id)
        }
    }
    
    def list() {
        AnagraphicDomain.withTransaction {
            AnagraphicDomain.list()
        }
    }
    def list(int max, int offset) {
        log.info "[list] max = ${max}, offset = ${offset}"
        AnagraphicDomain.withTransaction {
            AnagraphicDomain.list(max: max, offset: offset)
        }
    }
    def list(int max, int offset, String sort, String order) {
        log.info "[list] max = ${max}, offset = ${offset}, sort = ${sort}, order = ${order}"
        AnagraphicDomain.withTransaction {
            AnagraphicDomain.list(max: max, offset: offset, sort: sort, order: order)
        }
    }
    
    def delete(Long id) {
        def anagraphic = AnagraphicDomain.get(id)
        anagraphic.delete(validate:false,deepValidate:false,flush: true)
    }
    
    List<FieldError> save(def anagraphic) {
        def errors = null
        if (anagraphic.save(validate:false,flush: true)) {
            return null
        } else {
            anagraphic.errors.allErrors
        }
    }
    
    def filter(Map filters, int max, int offset) {
        def c = AnagraphicDomain.createCriteria()
        c.list (max: max, offset: offset) {
            and {
                filters.each { k, v ->
                    ilike(k, "%${v}%")
                }
            }
        }
    }
    
    def filter(Map filters, int max, int offset, String sort, String sortOrder) {
        def c = AnagraphicDomain.createCriteria()
        c.list (max: max, offset: offset) {
            and {
                filters.each { k, v ->
                    ilike(k, "%${v}%")
                }
            }
            order(sort, sortOrder)
        }
    }
}

