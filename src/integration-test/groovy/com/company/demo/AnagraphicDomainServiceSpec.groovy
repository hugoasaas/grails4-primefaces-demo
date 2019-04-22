package com.company.demo

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class AnagraphicDomainServiceSpec extends Specification {

    AnagraphicDomainService anagraphicDomainService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new AnagraphicDomain(...).save(flush: true, failOnError: true)
        //new AnagraphicDomain(...).save(flush: true, failOnError: true)
        //AnagraphicDomain anagraphicDomain = new AnagraphicDomain(...).save(flush: true, failOnError: true)
        //new AnagraphicDomain(...).save(flush: true, failOnError: true)
        //new AnagraphicDomain(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //anagraphicDomain.id
    }

    void "test get"() {
        setupData()

        expect:
        anagraphicDomainService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<AnagraphicDomain> anagraphicDomainList = anagraphicDomainService.list(max: 2, offset: 2)

        then:
        anagraphicDomainList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        anagraphicDomainService.count() == 5
    }

    void "test delete"() {
        Long anagraphicDomainId = setupData()

        expect:
        anagraphicDomainService.count() == 5

        when:
        anagraphicDomainService.delete(anagraphicDomainId)
        sessionFactory.currentSession.flush()

        then:
        anagraphicDomainService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        AnagraphicDomain anagraphicDomain = new AnagraphicDomain()
        anagraphicDomainService.save(anagraphicDomain)

        then:
        anagraphicDomain.id != null
    }
}
