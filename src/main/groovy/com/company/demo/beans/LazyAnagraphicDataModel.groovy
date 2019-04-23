package com.company.demo.beans

import grails.util.Holders
import org.apache.log4j.Logger
import com.company.demo.*
import org.primefaces.model.LazyDataModel
import org.primefaces.model.SortOrder
import javax.persistence.EntityManagerFactory

/**
* The is example lazy model for Anagraphic domain class
* source file https://github.com/andreaminnucci/primefaces
* Modify &amp; Improve: Prominic -Bing Li
* Data: 2019-04-21
*/

public class LazyAnagraphicDataModel extends LazyDataModel<AnagraphicDomain> {
    Logger log = Logger.getLogger(LazyAnagraphicDataModel.class)
            
    def anagraphicService
    
    public LazyAnagraphicDataModel() {
        this.anagraphicService = Holders.grailsApplication.mainContext.getBean 'anagraphicService'
    }
    
    @Override
    public List<AnagraphicDomain> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {

        log.info("21 first = " + first + ", pageSize = " + pageSize + ", sortField = " + sortField + ", sortOrder = " + sortOrder + ", filters = " + filters)
        def anagraphics
        def listAgn
        AnagraphicDomain.withTransaction {
            if (!sortField) {
                if (filters.size() == 0)
                    anagraphics = anagraphicService.list(pageSize, first)
                else
                    anagraphics = anagraphicService.filter(filters, pageSize, first)
            } else {
                String order = sortOrder == SortOrder.ASCENDING ? "asc" : "desc"
                if (filters.size() == 0)
                    anagraphics = anagraphicService.list(pageSize, first, sortField, order)
                else
                    anagraphics = anagraphicService.filter(filters, pageSize, first, sortField, order)
            }
            this.setRowCount(anagraphics.totalCount)
        }

        return anagraphics
    }

    @Override
    public Object getRowKey(AnagraphicDomain object) {
        log.debug("getRowKey('$object')")
        return object.id
    }

    @Override
    public AnagraphicDomain getRowData(String rowKey) {
        log.debug("getRowData('$rowKey')")
        try {
            Long rowKeyLong = Long.parseLong(rowKey)
            return anagraphicService.get(rowKeyLong)
        }
        catch (Exception ex) {
            log.error("Unable to lookup AnagraphicDomain object for key '$rowKey':  ", ex)
        }
    }
}

