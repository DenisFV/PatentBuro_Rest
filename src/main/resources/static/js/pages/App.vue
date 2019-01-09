<template>
    <v-app class="main-app">
        <v-toolbar app>
            <v-toolbar-title>RisePatent</v-toolbar-title>
            <v-spacer></v-spacer>
            <span v-if="profile">{{profile.name}}</span>
            <v-btn v-if="profile" icon href="/logout">
                <v-icon>exit_to_app</v-icon>
            </v-btn>
        </v-toolbar>
        <v-content>
            <v-container v-if="!profile">Необходимо авторизоваться через
                <a href="/login">Google</a>
            </v-container>
            <v-container v-if="profile">
                <requests-list :requests="requests" :profile="profile"/>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    import RequestsList from 'components/requests/RequestList.vue'
    import {addHandler} from "util/ws";

    export default {
        components: {
            RequestsList
        },
        data() {
            return {
                requests: frontendData.requests,
                profile: frontendData.profile
            }
        },
        created() {
            addHandler(data => {
                if (data.objectType === 'MESSAGE') {
                    const index = this.requests.findIndex(item => item.id === data.body.id)

                    switch (data.eventType) {
                        case 'CREATE':
                        case 'UPDATE':
                            if (index > -1) {
                                this.requests.splice(index, 1, data.body)
                            } else {
                                this.requests.push(data.body)
                            }
                            break
                        case 'REMOVE':
                            this.requests.splice(index, 1)
                            break
                        default:
                            console.error(`Looks like the event type if unknown "${data.eventType}"`)
                    }
                } else {
                    console.error('Неизвестное значение в enum-классе ObjectType: "${data.objectType}"')
                }
            })
        }
    }
</script>

<style scoped>

</style>