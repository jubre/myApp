package com.jubre

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TrucoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Truco.list(params), model:[trucoCount: Truco.count()]
    }

    def show(Truco truco) {
        respond truco
    }

    def create() {
        respond new Truco(params)
    }

    @Transactional
    def save(Truco truco) {
        if (truco == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (truco.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond truco.errors, view:'create'
            return
        }

        truco.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'truco.label', default: 'Truco'), truco.id])
                redirect truco
            }
            '*' { respond truco, [status: CREATED] }
        }
    }

    def edit(Truco truco) {
        respond truco
    }

    @Transactional
    def update(Truco truco) {
        if (truco == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (truco.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond truco.errors, view:'edit'
            return
        }

        truco.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'truco.label', default: 'Truco'), truco.id])
                redirect truco
            }
            '*'{ respond truco, [status: OK] }
        }
    }

    @Transactional
    def delete(Truco truco) {

        if (truco == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        truco.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'truco.label', default: 'Truco'), truco.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'truco.label', default: 'Truco'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
