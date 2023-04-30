<template>
  <div class="common-layout">
    <el-container v-loading="accountTree == null">
      <el-header> Header </el-header>
      <el-main>
        <el-table v-if="accountTree" border :data="accountTree.children" row-key="accountId" default-expand-all>
          <el-table-column label="Name" prop="name">
            <template #default="scope">
              <el-text size="default" class="account-name">
                <el-image
                  v-if="scope.row.icon !== 'none'"
                  :src="getIcon(scope.row.icon)"
                  style="width: 30px; height: 30px"
                ></el-image>
                <el-divider direction='vertical' border-style='none'></el-divider>
                {{ scope.row.name }}
              </el-text>
            </template>
          </el-table-column>
          <el-table-column label="Description" prop="description" />
          <el-table-column label="Summary" />
        </el-table>
      </el-main>
    </el-container>
  </div>
</template>

<script lang="ts">
import { mapMutations, mapState } from 'vuex'
import { AccountTree } from './entity'

export default {
  computed: {
    ...mapState(['accountTree'])
  },
  async mounted(): void {
    const response = await (await fetch(`${window.api.APP_URL}/api/account/tree`)).json()
    const accountTree: AccountTree = response.data.accountTree
    this.setAccountTree(accountTree)
  },
  methods: {
    ...mapMutations(['setAccountTree']),
    getIcon(icon: string): string {
      return new URL(`./assets/${icon}.svg`, import.meta.url).href
    }
  }
}
</script>

<style lang="less">
.cell {
  display: flex;
  align-items: center;
}

.account-name {
  display: flex;
  flex-wrap: nowrap;
  align-items: center;
}
</style>
