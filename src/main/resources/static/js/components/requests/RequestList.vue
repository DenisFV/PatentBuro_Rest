<template>
    <v-layout align-space-around justify-start column>
        <div v-if="profile.name !== admin">
            <request-form :requests="requests" :requestAttr="request" :profile="profile"/>
        </div>

        <div v-if="profile.name === admin">
            <v-container fluid grid-list-sm>
                <v-layout row wrap>
                    <v-flex v-for="request in sortedRequests" :key="request.id" xs4>
                            <request-row :request="request"
                                         :editRequest="editRequest"
                                         :deleteRequest="deleteRequest"
                            />
                    </v-flex>
                </v-layout>
            </v-container>
        </div>

        <div v-else>
            <v-container fluid grid-list-sm>
                <v-layout row wrap>
                    <v-flex v-for="request in sortedAuthors" :key="request.id" xs4>
                            <request-row :request="request"
                                         :editRequest="editRequest"
                                         :deleteRequest="deleteRequest"/>
                    </v-flex>
                </v-layout>
            </v-container>
        </div>
    </v-layout>
</template>

<script>
    import RequestRow from 'components/requests/RequestRow.vue'
    import RequestForm from 'components/requests/RequestForm.vue'
    import requestsApi from 'api/requests'

    export default {
        props: ['requests', 'profile'],
        components: {
            RequestRow,
            RequestForm
        },
        data() {
            return {
                request: null,
                admin: 'admin'
            }
        },
        computed: {
            sortedAuthors() {
                return this.requests
                    .filter(a => a.id !== undefined)
                    .filter(a => a.author.name === this.profile.name)
                    .sort((a, b) => (a.id - b.id))
            },
            sortedRequests() {
                return this.requests.sort((a, b) => (a.id - b.id))
            }
        },
        methods: {
            editRequest(request) {
                this.request = request
            },
            deleteRequest(request) {
                requestsApi.remove(request.id).then(result => {
                    if (result.ok)
                        this.requests.splice(this.requests.indexOf(request), 1)
                })
            }
        }
    }
</script>

<style scoped>

</style>