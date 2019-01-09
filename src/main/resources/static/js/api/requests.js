import Vue from 'vue'

const requests = Vue.resource('/request{/id}')

export default {
    add: request => requests.save({}, request),
    update: request => requests.update({id: request.id},request),
    remove: id => requests.remove({id})
}