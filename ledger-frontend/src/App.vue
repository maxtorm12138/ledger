<script lang="ts">
import {portalAccountTree,accountTree} from './api/api.ts'
export default {
    data() {
        return {
            accountTree: Array<accountTree>()
        }
    },
    async mounted() {
        this.accountTree = [await portalAccountTree()];
    }
}
</script>

<template>
    <t-layout>
        <t-aside>
            <t-menu>
                <t-submenu value="account" title="账户">
                    <template #icon>
                        <t-icon name="folder"/>
                    </template>
                    <t-tree :data="accountTree" :keys="{label: 'name'}">
                    </t-tree>
                </t-submenu>
                <t-menu-item>
                    <template #icon>
                        <t-icon name="user"/>
                    </template>
                    收款人
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
</style>
