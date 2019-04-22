package grails4.primefaces.demo
import com.company.demo.AnagraphicDomain
class BootStrap {

    def init = { servletContext ->
       log.info "BootStrap demo"

        def t1 = System.currentTimeMillis()
        def input
        int entryCount = 0
        try {
            AnagraphicDomain.withTransaction {
                if(AnagraphicDomain.list().size()<=0) {
                    input = servletContext.getResourceAsStream("/WEB-INF/resources/data.csv")

                    // input will be null when using "run-command <command>", so make this robust
                    input?.eachLine { line ->
                        entryCount++
                        def a = new AnagraphicDomain(firstName: line.split(";")[0], surname: line.split(";")[1])
                        //   println a.firstName
                        if (!a.save()) {
                            log.error a.errors
                        }
                    }
                }
            }
        } finally {
            if (input)
                input.close()
        }
        def t2 = System.currentTimeMillis()
        def delay = t2 - t1
        log.info "Generated ${entryCount} AnagraphicDomain objects in ${delay} milliseconds"

        AnagraphicDomain.withTransaction {
            log.info "Found ${AnagraphicDomain.count()} AnagraphicDomain instances."
        }

    }
    def destroy = {
    }
}
