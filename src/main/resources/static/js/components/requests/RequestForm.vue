<template>
    <div>
        <v-text-field label="Описание изделия" placeholder="Введите описание изделия" v-model="text"/>
        <v-text-field label="Тип изделия" placeholder="Укажите тип изделия" v-model="ptype"/>
        <input type="file" title="Выбрать файл" id="inp" v-on:change="fileSave"/>
        <v-btn small style="margin: 4px;" @click="save">Отправить</v-btn>
    </div>
</template>

<script>
    import requestsApi from 'api/requests'
    export default {
        props: ['requests', 'requestAttr', 'profile'],
        data() {
            return {
                id: '',
                text: '',
                ptype: '',
                filename: '',
                author: this.profile
            }
        },
        watch: {
            requestAttr(newVal, oldVal) {
                this.id = newVal.id
                this.text = newVal.text
                this.ptype = newVal.ptype
                this.filename = newVal.filename
                this.author = newVal.author
            }
        },
        methods: {
            fileSave(e) {
                this.filename = e.target.files[0].name
                console.log(this.filename)
            },
            save() {
                const request = {
                    id: this.id, text: this.text, ptype: this.ptype, filename: this.filename,
                    author: this.author
                }

                if (this.id) {
                    requestsApi.update(request).then(result => {
                        result.json().then(data => {
                            const index = this.requests.findIndex(item => item.id === data.id)
                            this.requests.splice(index, 1, data)
                        })
                    })
                } else {
                    requestsApi.add(request).then(result =>
                        result.json().then(data => {
                            const index = this.requests.findIndex(item => item.id === data.id)
                            if (index > -1)
                                this.requests.splice(index, 1, data)
                            else
                                this.requests.push(data)
                        })
                    )
                }
                this.id = ''
                this.text = ''
                this.ptype = ''
                this.filename = ''
                inp.value = ''
            }
        }
    }
</script>

<style scoped>

</style>