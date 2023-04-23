<script lang="ts">
import {mapMutations, mapState} from "vuex";
import {api} from "./api/api";
import {AccountTree} from "./api/entity";

export default {
    computed: {
        ...mapState({
            accountTree: 'accountTree'
        }),
    },
    methods: {
        ...mapMutations([
            'setAccountTree'
        ]),
    },
    async mounted() {
        this.setAccountTree(await api.accountTree());
    }
}
</script>

<template>
    <t-layout>
        <t-aside>
            <t-menu>
                <t-submenu value="account" title="account">
                    <template #icon>
                        <t-icon name="folder"/>
                    </template>
                    <t-tree :data="accountTree.children" id="account-tree" v-if="accountTree != null">
                        <template #icon="{ node }">
                            <t-icon v-if="node.getChildren() && !node.expanded" name="add" />
                            <t-icon v-else-if="node.getChildren() && node.expanded" name="remove" />
                        </template>
                        <template #label="{node}">
                            <t-avatar :image="`/static/${node.data.iconId}`" shape="round" size="20px"></t-avatar>
                            {{node.data.name}}
                        </template>
                    </t-tree>
                </t-submenu>
                <t-menu-item>
                    <template #icon>
                        <t-icon name="user"/>
                    </template>
                    transaction
                </t-menu-item>
                <t-menu-item>

                </t-menu-item>
            </t-menu>
        </t-aside>
        <t-layout>
            <t-content>
                content
            </t-content>
        </t-layout>
    </t-layout>
</template>
<style scoped>
#account-tree {
    margin: 0 var(--td-comp-margin-xl);
}
</style>
