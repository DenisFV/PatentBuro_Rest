<template>
    <v-card class="ma-2 grey lighten-4">
        <v-toolbar flat class="grey lighten-5">
            <div class="flex body">{{ request.id }}. {{request.author.name}}</div>
        </v-toolbar>
        <v-subheader>
            <div class="flex title">{{request.ptype}}</div>
        </v-subheader>
        <v-container fluid grid-list-sm>
            <v-layout row wrap>{{ request.text }}</v-layout>
        </v-container>

        <v-layout align-center justify-center class="pa-3">
            <a :href=request.filename>{{request.filename}}</a>
        </v-layout>


        <v-footer class="white">
            <v-btn @click="edit" small flat round>Изменить</v-btn>
            <v-spacer></v-spacer>
            <v-btn icon @click="del" small>
                <v-icon>delete</v-icon>
            </v-btn>
        </v-footer>
    </v-card>
</template>

<script>
    export default {
        props: ['request', 'editRequest', 'deleteRequest'],

        computed: {
            fun() {
                if (this.request.author === undefined) console.log('11111')
                console.log('00000')
            }
        },
        methods: {
            edit() {
                this.editRequest(this.request)
            }
            ,
            del() {
                this.deleteRequest(this.request)
            }
        }
    }
</script>

<style scoped>

</style>