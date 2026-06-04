<template>
  <base-layout>
    <view class="profile-page relative box-border min-h-screen w-full bg-secondary-900 text-text-primary-50">
      <view class="absolute inset-0 bg-gradient-to-b from-secondary-800 via-secondary-900 to-secondary-900" />
      <view class="halo-one absolute left-0 top-0 size-72 rounded-full bg-primary-500 opacity-30 blur-3xl" />
      <view class="halo-two absolute right-0 top-64 size-56 rounded-full bg-warning-500 opacity-20 blur-3xl" />
      <view class="halo-three absolute bottom-32 left-8 size-64 rounded-full bg-success-500 opacity-20 blur-3xl" />
      <view class="cockpit-grid absolute inset-0 opacity-30" />
      <view class="cockpit-vignette absolute inset-0" />

      <scroll-view
        scroll-y
        class="relative z-10 box-border h-screen w-full"
        refresher-enabled
        :refresher-triggered="refreshing"
        @refresherrefresh="handlePullRefresh"
      >
        <view class="profile-content box-border w-full p-2 pt-12">
          <view class="animate__animated animate__fadeInDown mb-5 px-3">
            <view class="flex items-center justify-between">
              <view class="flex-1">
                <text class="block text-overline tracking-widest text-primary-200">
                  DRIVER COMMAND CENTER
                </text>
                <text class="mt-2 block text-hero text-text-primary-50">
                  我的驾驶舱
                </text>
                <text class="mt-2 block truncate text-body text-text-primary-300">
                  账户、车辆、钱包与消息状态总览
                </text>
              </view>
              <view
                class="active-card flex size-12 items-center justify-center rounded-3xl border border-primary-100 border-opacity-20 bg-primary-100 bg-opacity-10 shadow-xl"
                @click="refreshAll"
              >
                <base-icon
                  type="refresh-filled"
                  size="22"
                  color="#f8fafc"
                />
              </view>
            </view>
          </view>

          <view
            v-if="isLoading"
            class="loading-card animate__animated animate__fadeIn mx-3 mb-4"
          >
            <view class="loading-orbit">
              <view class="loading-ring" />
              <view class="loading-core">
                <base-icon
                  type="person-filled"
                  size="24"
                  color="#ffb400"
                />
              </view>
            </view>
            <view class="ml-4 flex-1">
              <text class="block text-subtitle text-text-primary-50">
                正在同步资料
              </text>
              <text class="mt-1 block text-caption text-text-primary-400">
                请稍候，正在加载司机账户信息
              </text>
            </view>
          </view>

          <view class="hero-card animate__animated animate__fadeInUp mx-3 mb-4">
            <image
              class="absolute inset-0 size-full opacity-35"
              mode="aspectFill"
              src="https://www.codeflying.net/preview/city-bridge.jpg"
            />
            <view class="absolute inset-0 bg-gradient-to-t from-secondary-900 via-secondary-900 to-transparent opacity-90" />

            <view class="relative z-10 p-5">
              <view class="flex items-start gap-4">
                <view
                  class="avatar-wrap active-card"
                  @click="shareProfile"
                >
                  <image
                    class="size-full rounded-3xl"
                    mode="aspectFill"
                    :src="avatarUrl"
                  />
                </view>

                <view class="flex-1">
                  <view class="flex items-center gap-2">
                    <text class="truncate text-title text-text-primary-50">
                      {{ displayName }}
                    </text>
                    <view class="rounded-full border border-success-400 border-opacity-30 bg-success-500 bg-opacity-20 px-2 py-1">
                      <text class="text-caption text-success-100">
                        {{ userInfo.status || '未认证' }}
                      </text>
                    </view>
                  </view>
                  <text class="mt-2 block truncate text-body text-text-primary-300">
                    {{ userInfo.phone_number || '暂无手机号' }}
                  </text>
                  <view class="mt-3 flex items-center gap-2">
                    <base-icon
                      type="location-filled"
                      size="16"
                      color="#c3d0dd"
                    />
                    <text class="truncate text-caption text-primary-100">
                      {{ userInfo.city || '城市未设置' }}
                    </text>
                  </view>
                </view>
              </view>

              <view class="mt-6 grid grid-cols-2 gap-3">
                <view class="metric-card">
                  <text class="block text-caption text-primary-200">
                    累计活跃
                  </text>
                  <view class="mt-1 flex items-end gap-1">
                    <text class="text-hero text-warning-500">
                      {{ userInfo.active_days || 0 }}
                    </text>
                    <text class="mb-1 text-caption text-text-primary-300">
                      天
                    </text>
                  </view>
                </view>
                <view class="metric-card">
                  <text class="block text-caption text-primary-200">
                    连续在线
                  </text>
                  <view class="mt-1 flex items-end gap-1">
                    <text class="text-hero text-success-500">
                      {{ userInfo.continue_active_days || 0 }}
                    </text>
                    <text class="mb-1 text-caption text-text-primary-300">
                      天
                    </text>
                  </view>
                </view>
              </view>
            </view>
          </view>

          <view class="mx-3 mb-4 grid grid-cols-2 gap-3">
            <view
              class="glass-card active-card animate__animated animate__fadeInUp p-4"
              @click="openMessageCenter"
            >
              <view class="flex items-center justify-between">
                <view class="flex size-11 items-center justify-center rounded-3xl border border-danger-400 border-opacity-30 bg-danger-500 bg-opacity-20">
                  <base-icon
                    type="notification-filled"
                    size="22"
                    color="#f37373"
                  />
                </view>
                <uni-badge
                  v-if="unreadCount > 0"
                  :text="unreadCount"
                  type="error"
                  size="normal"
                />
              </view>
              <text class="mt-4 block text-subtitle text-text-primary-50">
                消息中心
              </text>
              <text class="mt-1 block truncate text-caption text-text-primary-400">
                {{ unreadCount > 0 ? '有未读消息待处理' : '全部消息已读' }}
              </text>
            </view>

            <view
              class="glass-card active-card animate__animated animate__fadeInUp p-4"
              @click="openInvitePoster"
            >
              <view class="flex items-center justify-between">
                <view class="flex size-11 items-center justify-center rounded-3xl border border-warning-400 border-opacity-30 bg-warning-500 bg-opacity-20">
                  <base-icon
                    type="gift-filled"
                    size="22"
                    color="#ffb400"
                  />
                </view>
                <base-icon
                  type="right"
                  size="18"
                  color="#94a3b8"
                />
              </view>
              <text class="mt-4 block text-subtitle text-text-primary-50">
                邀请好友
              </text>
              <text class="mt-1 block truncate text-caption text-text-primary-400">
                复制专属邀请码
              </text>
            </view>
          </view>

          <view class="section-card animate__animated animate__fadeInUp mx-3 mb-4">
            <view class="mb-4 flex items-center justify-between">
              <view>
                <text class="block text-overline tracking-widest text-primary-200">
                  VEHICLE PROFILE
                </text>
                <text class="mt-1 block text-title text-text-primary-50">
                  车辆信息
                </text>
              </view>
              <view class="flex size-12 items-center justify-center rounded-3xl border border-primary-200 border-opacity-20 bg-primary-500 bg-opacity-20">
                <base-icon
                  type="cart-filled"
                  size="24"
                  color="#c3d0dd"
                />
              </view>
            </view>

            <view class="info-row">
              <view class="row-icon bg-primary-500 bg-opacity-20">
                <base-icon
                  type="flag-filled"
                  size="18"
                  color="#c3d0dd"
                />
              </view>
              <view class="flex-1">
                <text class="block text-caption text-text-primary-400">
                  车牌号
                </text>
                <text class="mt-1 block truncate text-subtitle text-text-primary-50">
                  {{ userInfo.car_plate || '暂无车牌' }}
                </text>
              </view>
            </view>

            <view class="info-row">
              <view class="row-icon bg-success-500 bg-opacity-20">
                <base-icon
                  type="settings-filled"
                  size="18"
                  color="#67c67f"
                />
              </view>
              <view class="flex-1">
                <text class="block text-caption text-text-primary-400">
                  车辆类型
                </text>
                <text class="mt-1 block truncate text-subtitle text-text-primary-50">
                  {{ userInfo.type_name || '未设置' }}
                </text>
              </view>
            </view>

            <view class="info-row">
              <view class="row-icon bg-warning-500 bg-opacity-20">
                <base-icon
                  type="medal-filled"
                  size="18"
                  color="#ffb400"
                />
              </view>
              <view class="flex-1">
                <text class="block text-caption text-text-primary-400">
                  营运证状态
                </text>
                <text class="mt-1 block truncate text-subtitle text-text-primary-50">
                  {{ certification.status || userInfo.status || '未认证' }}
                </text>
              </view>
              <view class="rounded-full border border-primary-100 border-opacity-20 bg-primary-100 bg-opacity-10 px-3 py-1">
                <text class="text-caption text-primary-100">
                  {{ certificationLabel }}
                </text>
              </view>
            </view>
          </view>

          <view class="wallet-card animate__animated animate__fadeInUp mx-3 mb-4">
            <view class="flex items-start justify-between">
              <view>
                <text class="block text-overline tracking-widest text-warning-100">
                  WALLET & MEMBERSHIP
                </text>
                <text class="mt-2 block text-body text-primary-100">
                  可用余额
                </text>
                <view class="mt-1 flex items-end gap-1">
                  <text class="wallet-money">
                    ¥{{ formatMoney(wallet.balance) }}
                  </text>
                </view>
              </view>
              <view class="flex size-14 items-center justify-center rounded-3xl border border-warning-400 border-opacity-30 bg-secondary-900 bg-opacity-50">
                <base-icon
                  type="wallet-filled"
                  size="28"
                  color="#ffb400"
                />
              </view>
            </view>

            <view class="mt-5 grid grid-cols-2 gap-3">
              <view class="mini-panel">
                <text class="block text-caption text-text-primary-400">
                  会员类型
                </text>
                <text class="mt-1 block truncate text-subtitle text-text-primary-50">
                  {{ wallet.type_name || '未开通' }}
                </text>
              </view>
              <view class="mini-panel">
                <text class="block text-caption text-text-primary-400">
                  到期时间
                </text>
                <text class="mt-1 block truncate text-subtitle text-text-primary-50">
                  {{ wallet.member_expire || '暂无' }}
                </text>
              </view>
            </view>

            <view class="mt-5 flex gap-3">
              <view
                class="btn-core active-press flex-1"
                @click="openRechargePopup"
              >
                <view class="btn-shine" />
                <base-icon
                  type="plus-filled"
                  size="18"
                  color="#f8fafc"
                />
                <text class="btn-text">
                  余额充值
                </text>
              </view>
              <view
                class="btn-secondary active-press flex-1"
                @click="openWalletRecord"
              >
                <base-icon
                  type="list"
                  size="18"
                  color="#e2e8f0"
                />
                <text class="btn-secondary-text">
                  订单记录
                </text>
              </view>
            </view>
          </view>

          <view class="section-card animate__animated animate__fadeInUp mx-3 mb-4">
            <view class="mb-4 flex items-center justify-between">
              <view>
                <text class="block text-overline tracking-widest text-primary-200">
                  INVITATION NETWORK
                </text>
                <text class="mt-1 block text-title text-text-primary-50">
                  邀请记录
                </text>
              </view>
              <view
                class="active-card rounded-full border border-warning-400 border-opacity-30 bg-warning-500 bg-opacity-20 px-3 py-2"
                @click="shareInviteCode"
              >
                <text class="text-caption text-warning-100">
                  复制邀请码
                </text>
              </view>
            </view>

            <view class="invite-code-card mb-4">
              <view>
                <text class="block text-caption text-primary-200">
                  专属邀请码
                </text>
                <text class="invite-code mt-1 block">
                  {{ inviteCode }}
                </text>
              </view>
              <base-icon
                type="paperplane-filled"
                size="24"
                color="#ffb400"
              />
            </view>

            <view
              v-if="inviteRecords.length == 0"
              class="empty-state"
            >
              <base-icon
                type="personadd"
                size="28"
                color="#94a3b8"
              />
              <text class="mt-2 block text-caption text-text-primary-400">
                暂无邀请记录，分享邀请码开始拓展车友网络
              </text>
            </view>

            <view
              v-for="item in inviteRecords"
              :key="item.invite_record_id"
              class="record-row"
            >
              <view class="row-icon bg-primary-500 bg-opacity-20">
                <base-icon
                  type="personadd-filled"
                  size="18"
                  color="#c3d0dd"
                />
              </view>
              <view class="flex-1">
                <text class="block truncate text-subtitle text-text-primary-50">
                  {{ item.invitee_phone }}
                </text>
                <text class="mt-1 block truncate text-caption text-text-primary-400">
                  {{ item.invite_time || '邀请时间待同步' }}
                </text>
              </view>
            </view>

            <view class="rule-card mt-4">
              <text class="block text-caption text-warning-100">
                邀请规则
              </text>
              <text class="mt-2 block text-body text-text-primary-300">
                邀请好友完成注册与认证后，可获得平台活动奖励。奖励规则以平台最新公告为准，请保持账号状态正常。
              </text>
            </view>
          </view>

          <view class="section-card animate__animated animate__fadeInUp mx-3 mb-4">
            <view class="mb-4 flex items-center justify-between">
              <view>
                <text class="block text-overline tracking-widest text-primary-200">
                  MESSAGE BRIEFING
                </text>
                <text class="mt-1 block text-title text-text-primary-50">
                  最新消息
                </text>
              </view>
              <view
                class="text-link active-card"
                @click="markAllMessagesRead"
              >
                <text class="text-caption text-warning-100">
                  全部已读
                </text>
              </view>
            </view>

            <scroll-view
              scroll-x
              class="mb-4 w-full"
            >
              <view class="inline-flex gap-3 pb-2">
                <view
                  class="filter-chip"
                  :class="selectedMessageType == 0 ? 'filter-chip-active' : ''"
                  @click="changeMessageType(0)"
                >
                  <text class="text-caption">
                    全部
                  </text>
                </view>
                <view
                  v-for="item in messageTypes"
                  :key="item.message_type_enum_id"
                  class="filter-chip"
                  :class="selectedMessageType == item.message_type_enum_id ? 'filter-chip-active' : ''"
                  @click="changeMessageType(item.message_type_enum_id)"
                >
                  <text class="text-caption">
                    {{ item.type_name }}
                  </text>
                </view>
              </view>
            </scroll-view>

            <view
              v-if="messages.length == 0"
              class="empty-state"
            >
              <base-icon
                type="mail-open"
                size="28"
                color="#94a3b8"
              />
              <text class="mt-2 block text-caption text-text-primary-400">
                当前分类暂无消息
              </text>
            </view>

            <view
              v-for="item in messages"
              :key="item.message_center_id"
              class="message-row active-card"
              @click="openMessageDetail(item)"
            >
              <view
                class="message-dot"
                :class="item.is_read ? 'message-dot-read' : 'message-dot-unread'"
              />
              <view class="flex-1">
                <view class="flex items-center gap-2">
                  <text class="flex-1 truncate text-subtitle text-text-primary-50">
                    {{ item.title }}
                  </text>
                  <text class="text-caption text-primary-200">
                    {{ item.type_name || '消息' }}
                  </text>
                </view>
                <text class="mt-1 block truncate text-caption text-text-primary-400">
                  {{ item.create_time || '时间待同步' }}
                </text>
              </view>
              <base-icon
                type="right"
                size="16"
                color="#94a3b8"
              />
            </view>
          </view>

          <view class="section-card animate__animated animate__fadeInUp mx-3 mb-4">
            <view class="mb-4">
              <text class="block text-overline tracking-widest text-primary-200">
                SYSTEM SETTINGS
              </text>
              <text class="mt-1 block text-title text-text-primary-50">
                设置
              </text>
            </view>

            <view
              class="setting-row active-card"
              @click="openFeedbackPopup"
            >
              <view class="row-icon bg-success-500 bg-opacity-20">
                <base-icon
                  type="chatbubble-filled"
                  size="18"
                  color="#67c67f"
                />
              </view>
              <view class="flex-1">
                <text class="block text-subtitle text-text-primary-50">
                  意见反馈
                </text>
                <text class="mt-1 block text-caption text-text-primary-400">
                  提交体验建议与问题描述
                </text>
              </view>
              <base-icon
                type="right"
                size="16"
                color="#94a3b8"
              />
            </view>

            <view
              class="setting-row active-card"
              @click="clearCache"
            >
              <view class="row-icon bg-primary-500 bg-opacity-20">
                <base-icon
                  type="trash-filled"
                  size="18"
                  color="#c3d0dd"
                />
              </view>
              <view class="flex-1">
                <text class="block text-subtitle text-text-primary-50">
                  清理缓存
                </text>
                <text class="mt-1 block text-caption text-text-primary-400">
                  释放本地临时数据
                </text>
              </view>
              <base-icon
                type="right"
                size="16"
                color="#94a3b8"
              />
            </view>

            <view
              class="setting-row active-card"
              @click="openAboutPopup"
            >
              <view class="row-icon bg-warning-500 bg-opacity-20">
                <base-icon
                  type="info-filled"
                  size="18"
                  color="#ffb400"
                />
              </view>
              <view class="flex-1">
                <text class="block text-subtitle text-text-primary-50">
                  关于我们
                </text>
                <text class="mt-1 block text-caption text-text-primary-400">
                  查看平台服务理念
                </text>
              </view>
              <base-icon
                type="right"
                size="16"
                color="#94a3b8"
              />
            </view>

            <view
              class="setting-row active-card"
              @click="logout"
            >
              <view class="row-icon bg-danger-500 bg-opacity-20">
                <base-icon
                  type="locked-filled"
                  size="18"
                  color="#f37373"
                />
              </view>
              <view class="flex-1">
                <text class="block text-subtitle text-danger-100">
                  退出登录
                </text>
                <text class="mt-1 block text-caption text-text-primary-400">
                  安全退出当前司机账号
                </text>
              </view>
              <base-icon
                type="right"
                size="16"
                color="#94a3b8"
              />
            </view>
          </view>

          <view class="bottom-safe" />
        </view>
      </scroll-view>

      <uni-popup
        ref="feedbackPopup"
        type="center"
      >
        <uni-forms
          class="popup-form box-border w-80 rounded-3xl bg-white p-4"
          label-position="top"
          label-width="100%"
        >
          <view class="mb-4 flex items-center justify-between">
            <view>
              <text class="block text-title text-secondary-900">
                意见反馈
              </text>
              <text class="mt-1 block text-caption text-text-secondary-500">
                你的建议会帮助我们优化司机体验
              </text>
            </view>
            <view
              class="active-card flex size-10 items-center justify-center rounded-3xl bg-secondary-900"
              @click="closeFeedbackPopup"
            >
              <base-icon
                type="close"
                size="18"
                color="#f8fafc"
              />
            </view>
          </view>

          <uni-forms-item
            label="反馈内容"
            name="content"
          >
            <uni-easyinput
              v-model="feedbackForm.content"
              type="textarea"
              maxlength="-1"
              placeholder="请输入你的问题或建议"
              class="box-border"
              @input="onFeedbackInput"
            />
          </uni-forms-item>

          <view
            class="popup-btn-primary active-press"
            @click="submitFeedback"
          >
            <base-icon
              type="paperplane-filled"
              size="18"
              color="#f8fafc"
            />
            <text class="popup-btn-text">
              提交反馈
            </text>
          </view>
        </uni-forms>
      </uni-popup>

      <uni-popup
        ref="rechargePopup"
        type="center"
      >
        <uni-forms
          class="popup-form box-border w-80 rounded-3xl bg-white p-4"
          label-position="top"
          label-width="100%"
        >
          <view class="mb-4 flex items-center justify-between">
            <view>
              <text class="block text-title text-secondary-900">
                余额充值
              </text>
              <text class="mt-1 block text-caption text-text-secondary-500">
                模拟充值将直接更新钱包余额
              </text>
            </view>
            <view
              class="active-card flex size-10 items-center justify-center rounded-3xl bg-secondary-900"
              @click="closeRechargePopup"
            >
              <base-icon
                type="close"
                size="18"
                color="#f8fafc"
              />
            </view>
          </view>

          <uni-forms-item
            label="充值金额"
            name="amount"
          >
            <uni-easyinput
              v-model="rechargeForm.amount"
              type="number"
              maxlength="-1"
              placeholder="请输入充值金额"
              class="box-border"
              @input="onRechargeInput"
            />
          </uni-forms-item>

          <view class="mb-4 grid grid-cols-3 gap-2">
            <view
              class="amount-chip active-card"
              @click="selectRechargeAmount(50)"
            >
              ¥50
            </view>
            <view
              class="amount-chip active-card"
              @click="selectRechargeAmount(100)"
            >
              ¥100
            </view>
            <view
              class="amount-chip active-card"
              @click="selectRechargeAmount(300)"
            >
              ¥300
            </view>
          </view>

          <view
            class="popup-btn-primary active-press"
            @click="submitRecharge"
          >
            <base-icon
              type="wallet-filled"
              size="18"
              color="#f8fafc"
            />
            <text class="popup-btn-text">
              确认充值
            </text>
          </view>
        </uni-forms>
      </uni-popup>

      <uni-popup
        ref="messagePopup"
        type="center"
      >
        <view class="popup-panel box-border w-80 rounded-3xl bg-white p-4">
          <view class="mb-4 flex items-center justify-between">
            <view class="flex-1">
              <text class="block truncate text-title text-secondary-900">
                {{ currentMessage.title || '消息详情' }}
              </text>
              <text class="mt-1 block text-caption text-text-secondary-500">
                {{ currentMessage.type_name || '平台消息' }} · {{ currentMessage.create_time || '时间待同步' }}
              </text>
            </view>
            <view
              class="active-card flex size-10 items-center justify-center rounded-3xl bg-secondary-900"
              @click="closeMessagePopup"
            >
              <base-icon
                type="close"
                size="18"
                color="#f8fafc"
              />
            </view>
          </view>
          <view class="message-content-box">
            <text class="text-body text-text-secondary-700">
              {{ currentMessage.content || '暂无详细内容' }}
            </text>
          </view>
          <view
            class="popup-btn-primary active-press mt-4"
            @click="markCurrentMessageRead"
          >
            <base-icon
              type="checkmarkempty"
              size="18"
              color="#f8fafc"
            />
            <text class="popup-btn-text">
              标记已读
            </text>
          </view>
        </view>
      </uni-popup>

      <uni-popup
        ref="aboutPopup"
        type="center"
      >
        <view class="popup-panel box-border w-80 rounded-3xl bg-white p-4">
          <view class="mb-4 flex items-center justify-between">
            <view>
              <text class="block text-title text-secondary-900">
                关于我们
              </text>
              <text class="mt-1 block text-caption text-text-secondary-500">
                专业司机效率平台
              </text>
            </view>
            <view
              class="active-card flex size-10 items-center justify-center rounded-3xl bg-secondary-900"
              @click="closeAboutPopup"
            >
              <base-icon
                type="close"
                size="18"
                color="#f8fafc"
              />
            </view>
          </view>
          <image
            class="mb-4 h-36 w-full rounded-3xl"
            mode="aspectFill"
            src="https://www.codeflying.net/preview/research-data-center.jpg"
          />
          <text class="block text-body text-text-secondary-700">
            我们致力于为司机提供更清晰的订单热力、导航辅助、钱包会员与消息协同能力，让每一次出车都更高效、更安心。
          </text>
        </view>
      </uni-popup>

      <uni-popup
        ref="invitePopup"
        type="center"
      >
        <view class="popup-panel box-border w-80 rounded-3xl bg-white p-4">
          <view class="poster-card">
            <image
              class="absolute inset-0 size-full opacity-35"
              mode="aspectFill"
              src="https://www.codeflying.net/preview/technological-lines.jpg"
            />
            <view class="absolute inset-0 bg-gradient-to-t from-secondary-900 via-secondary-800 to-transparent opacity-90" />
            <view class="relative z-10 p-5">
              <view class="mb-5 flex size-14 items-center justify-center rounded-3xl border border-warning-400 border-opacity-30 bg-warning-500 bg-opacity-20">
                <base-icon
                  type="gift-filled"
                  size="28"
                  color="#ffb400"
                />
              </view>
              <text class="block text-title text-text-primary-50">
                邀请车友加入
              </text>
              <text class="mt-2 block text-body text-text-primary-300">
                共享高效接单驾驶舱体验
              </text>
              <view class="poster-code mt-5">
                <text class="block text-caption text-warning-100">
                  专属邀请码
                </text>
                <text class="invite-code mt-1 block">
                  {{ inviteCode }}
                </text>
              </view>
            </view>
          </view>
          <view
            class="popup-btn-primary active-press mt-4"
            @click="shareInviteCode"
          >
            <base-icon
              type="paperplane-filled"
              size="18"
              color="#f8fafc"
            />
            <text class="popup-btn-text">
              复制并分享
            </text>
          </view>
          <view
            class="popup-btn-secondary active-press mt-3"
            @click="closeInvitePopup"
          >
            <text class="popup-btn-secondary-text">
              关闭
            </text>
          </view>
        </view>
      </uni-popup>
    </view>
  </base-layout>
</template>

<script setup>
const proxy = getCurrentInstance().proxy;

const isLoading = ref(false);
const refreshing = ref(false);
const isSubmittingFeedback = ref(false);
const isSubmittingRecharge = ref(false);
const isRefreshing = ref(false);

const userId = ref(0);
const userInfo = ref({});
const certification = ref({});
const wallet = ref({});
const inviteRecords = ref([]);
const messages = ref([]);
const messageTypes = ref([]);
const selectedMessageType = ref(0);
const unreadCount = ref(0);
const currentMessage = ref({});

const feedbackPopup = ref(null);
const rechargePopup = ref(null);
const messagePopup = ref(null);
const aboutPopup = ref(null);
const invitePopup = ref(null);

const feedbackForm = ref({
  content: '',
});

const rechargeForm = ref({
  amount: '',
});

const displayName = computed(() => {
  if (userInfo.value.real_name) {
    return userInfo.value.real_name;
  }
  if (userInfo.value.phone_number) {
    return '司机 ' + String(userInfo.value.phone_number).slice(-4);
  }
  return '司机用户';
});

const avatarUrl = computed(() => {
  const avatar = userInfo.value.avatar;
  if (Array.isArray(avatar) && avatar.length > 0 && avatar[0].url) {
    if (String(avatar[0].url).indexOf('http') == 0) {
      return avatar[0].url;
    }
    return proxy.get_resource_url(avatar[0].url);
  }
  return 'https://www.codeflying.net/preview/ai-cyber-city-robotman.jpg';
});

const certificationLabel = computed(() => {
  if (certification.value.user_certification_id) {
    return '资料已提交';
  }
  return '待提交';
});

const inviteCode = computed(() => {
  if (userId.value) {
    return 'DRV' + String(100000 + Number(userId.value));
  }
  return 'DRV000000';
});

onShow(() => {
  loadAllData();
});

function formatDate(date, format = 'YYYY-MM-DD HH:mm:ss') {
  const pad = n => String(n).padStart(2, '0');
  const map = {
    YYYY: date.getFullYear(),
    MM: pad(date.getMonth() + 1),
    DD: pad(date.getDate()),
    HH: pad(date.getHours()),
    mm: pad(date.getMinutes()),
    ss: pad(date.getSeconds()),
  };
  return format.replace(/YYYY|MM|DD|HH|mm|ss/g, match => map[match]);
}

function formatMoney(value) {
  const num = Number(value || 0);
  return num.toFixed(2);
}

async function loadAllData() {
  if (isRefreshing.value) {
    return;
  }
  isRefreshing.value = true;
  isLoading.value = true;

  const authRes = await proxy.$app.auth.getUser();
  if (authRes.success && authRes.data) {
    userId.value = Number(authRes.data.user_info_id || 0);
    await loadUserInfo();
    await loadCertification();
    await loadWallet();
    await loadInviteRecords();
    await loadMessageTypes();
    await loadMessages();
    await loadUnreadCount();
  }

  isLoading.value = false;
  refreshing.value = false;
  isRefreshing.value = false;
}

async function loadUserInfo() {
  if (!userId.value) {
    return;
  }
  const res = await proxy.$app.db.from('user_info').get().eq('user_info_id', userId.value);
  if (res.success && res.data) {
    userInfo.value = res.data;
  }
}

async function loadCertification() {
  if (!userId.value) {
    return;
  }
  const res = await proxy.$app.db.from('user_certification').page().page(1, 1).eq('user_info_user_info_id_1', userId.value).order('user_certification_id', 'desc');
  if (res.success && res.data && res.data.records && res.data.records.length > 0) {
    certification.value = res.data.records[0];
  } else {
    certification.value = {};
  }
}

async function loadWallet() {
  if (!userId.value) {
    return;
  }
  const res = await proxy.$app.db.from('wallet').page().page(1, 1).eq('user_info_user_info_id_1', userId.value).order('wallet_id', 'desc');
  if (res.success && res.data && res.data.records && res.data.records.length > 0) {
    wallet.value = res.data.records[0];
  } else {
    wallet.value = {};
  }
}

async function loadInviteRecords() {
  if (!userId.value) {
    return;
  }
  const res = await proxy.$app.db.from('invite_record').page().page(1, 5).eq('user_info_user_info_id_1', userId.value).order('invite_time', 'desc');
  if (res.success && res.data && res.data.records) {
    inviteRecords.value = res.data.records;
  }
}

async function loadMessageTypes() {
  const res = await proxy.$app.db.from('message_type_enum').page().page(1, 20).order('message_type_enum_id', 'asc');
  if (res.success && res.data && res.data.records) {
    messageTypes.value = res.data.records;
  }
}

async function loadMessages() {
  let query = proxy.$app.db.from('message_center').page().page(1, 8);
  if (selectedMessageType.value) {
    query = query.eq('type_enum_message_type_enum_id_1', selectedMessageType.value);
  }
  const res = await query.order('create_time', 'desc');
  if (res.success && res.data && res.data.records) {
    messages.value = res.data.records;
  }
}

async function loadUnreadCount() {
  const res = await proxy.$app.db.from('message_center').page().page(1, 100).eq('is_read', false);
  if (res.success && res.data && res.data.records) {
    unreadCount.value = res.data.records.length;
  }
}

function handlePullRefresh() {
  refreshing.value = true;
  loadAllData();
}

function refreshAll() {
  loadAllData();
}

function onFeedbackInput() {
  nextTick(() => {});
}

function onRechargeInput() {
  nextTick(() => {});
}

function openFeedbackPopup() {
  feedbackForm.value.content = '';
  nextTick(() => {
    feedbackPopup.value.open();
  });
}

function closeFeedbackPopup() {
  feedbackPopup.value.close();
}

async function submitFeedback() {
  if (isSubmittingFeedback.value) {
    return;
  }
  if (!feedbackForm.value.content || !String(feedbackForm.value.content).trim()) {
    await proxy.$app.ui.toast().level('error').message('请先填写反馈内容');
    return;
  }
  if (!userId.value) {
    await proxy.$app.ui.toast().level('error').message('用户信息未同步，请稍后重试');
    return;
  }

  isSubmittingFeedback.value = true;
  const res = await proxy.$app.db.from('feedback').insert().values({
    user_info_user_info_id_1: userId.value,
    content: String(feedbackForm.value.content).trim(),
    create_time: formatDate(new Date()),
  });

  if (res.success) {
    await proxy.$app.ui.toast().level('success').message('反馈已提交，感谢你的建议');
    closeFeedbackPopup();
  }

  isSubmittingFeedback.value = false;
}

function openRechargePopup() {
  rechargeForm.value.amount = '';
  nextTick(() => {
    rechargePopup.value.open();
  });
}

function closeRechargePopup() {
  rechargePopup.value.close();
}

function selectRechargeAmount(amount) {
  rechargeForm.value.amount = String(amount);
}

async function submitRecharge() {
  if (isSubmittingRecharge.value) {
    return;
  }
  const amount = Number(rechargeForm.value.amount || 0);
  if (!amount || amount <= 0) {
    await proxy.$app.ui.toast().level('error').message('请输入有效充值金额');
    return;
  }
  if (!wallet.value.wallet_id) {
    await proxy.$app.ui.toast().level('error').message('钱包信息未初始化，暂无法充值');
    return;
  }

  isSubmittingRecharge.value = true;
  const nextBalance = Number(wallet.value.balance || 0) + amount;
  const res = await proxy.$app.db.from('wallet').update().set({
    balance: nextBalance,
  }).eq('wallet_id', wallet.value.wallet_id);

  if (res.success) {
    await proxy.$app.ui.toast().level('success').message('充值成功，余额已刷新');
    closeRechargePopup();
    await loadWallet();
  }

  isSubmittingRecharge.value = false;
}

async function openWalletRecord() {
  await proxy.$app.ui.model()
    .title('订单记录')
    .message('当前钱包余额为 ¥' + formatMoney(wallet.value.balance) + '。充值记录将随平台账单能力同步展示。')
    .confirmText('知道了')
    .cancelText('关闭');
}

function openInvitePoster() {
  nextTick(() => {
    invitePopup.value.open();
  });
}

function closeInvitePopup() {
  invitePopup.value.close();
}

async function shareInviteCode() {
  await proxy.$app.devices.shareApi().type('linkOrText').text('我的司机邀请码：' + inviteCode.value + '，欢迎加入高效接单驾驶舱。');
  await proxy.$app.ui.toast().level('success').message('邀请码已复制，可分享给好友');
}

async function shareProfile() {
  await proxy.$app.devices.shareApi().type('linkOrText').text('司机名片：' + displayName.value + '，城市：' + (userInfo.value.city || '未设置') + '，车牌：' + (userInfo.value.car_plate || '未设置'));
  await proxy.$app.ui.toast().level('success').message('司机名片已复制');
}

function openMessageCenter() {
  selectedMessageType.value = 0;
  loadMessages();
}

async function changeMessageType(typeId) {
  selectedMessageType.value = typeId;
  await loadMessages();
}

function openMessageDetail(item) {
  currentMessage.value = item;
  nextTick(() => {
    messagePopup.value.open();
  });
}

function closeMessagePopup() {
  messagePopup.value.close();
}

async function markCurrentMessageRead() {
  if (!currentMessage.value.message_center_id) {
    return;
  }
  const res = await proxy.$app.db.from('message_center').update().set({
    is_read: true,
  }).eq('message_center_id', currentMessage.value.message_center_id);

  if (res.success) {
    await proxy.$app.ui.toast().level('success').message('已标记为已读');
    closeMessagePopup();
    await loadMessages();
    await loadUnreadCount();
  }
}

async function markAllMessagesRead() {
  const confirmRes = await proxy.$app.ui.model()
    .title('全部已读')
    .message('确认将当前消息列表标记为已读吗？')
    .confirmText('确认')
    .cancelText('取消');

  if (!confirmRes.confirm) {
    return;
  }

  let query = proxy.$app.db.from('message_center').update().set({
    is_read: true,
  }).eq('is_read', false);

  if (selectedMessageType.value) {
    query = query.eq('type_enum_message_type_enum_id_1', selectedMessageType.value);
  }

  const res = await query;
  if (res.success) {
    await proxy.$app.ui.toast().level('success').message('消息已全部标记为已读');
    await loadMessages();
    await loadUnreadCount();
  }
}

function openAboutPopup() {
  nextTick(() => {
    aboutPopup.value.open();
  });
}

function closeAboutPopup() {
  aboutPopup.value.close();
}

async function clearCache() {
  const confirmRes = await proxy.$app.ui.model()
    .title('清理缓存')
    .message('确认清理本地页面临时缓存吗？不会影响账号数据。')
    .confirmText('清理')
    .cancelText('取消');

  if (confirmRes.confirm) {
    uni.removeStorageSync('profileCache');
    uni.removeStorageSync('messageCache');
    await proxy.$app.ui.toast().level('success').message('缓存已清理');
  }
}

async function logout() {
  const confirmRes = await proxy.$app.ui.model()
    .title('退出登录')
    .message('确认退出当前司机账号吗？')
    .confirmText('退出')
    .cancelText('取消');

  if (!confirmRes.confirm) {
    return;
  }

  const res = await proxy.$app.auth.logout();
  if (res.success) {
    await proxy.$app.ui.toast().level('success').message('已安全退出');
    await proxy.$app.router.navigate.to().url('/pages/login/index').type('page');
  }
}
</script>

<style scoped>
.profile-page {
  max-width: 100vw;
  overflow-x: hidden;
}

.profile-content {
  padding-bottom: 84px;
}

.bottom-safe {
  height: 72px;
}

.halo-one {
  transform: translate(-35%, -30%);
}

.halo-two {
  transform: translate(24%, -10%);
}

.halo-three {
  transform: translate(-18%, 10%);
}

.cockpit-grid {
  background-image:
    linear-gradient(rgba(195, 208, 221, 0.08) 1px, transparent 1px),
    linear-gradient(90deg, rgba(195, 208, 221, 0.08) 1px, transparent 1px);
  background-size: 28px 28px;
}

.cockpit-vignette {
  background:
    linear-gradient(180deg, rgba(13, 27, 42, 0.08), rgba(13, 27, 42, 0.94)),
    linear-gradient(90deg, rgba(13, 27, 42, 0.72), transparent 48%, rgba(13, 27, 42, 0.72));
}

.hero-card {
  position: relative;
  overflow: hidden;
  min-height: 272px;
  border-radius: 34px;
  background: rgba(225, 231, 238, 0.1);
  border: 1px solid rgba(195, 208, 221, 0.18);
  box-shadow:
    0 24px 70px rgba(13, 27, 42, 0.62),
    inset 0 1px 0 rgba(248, 250, 252, 0.14);
}

.avatar-wrap {
  width: 76px;
  height: 76px;
  padding: 3px;
  border-radius: 28px;
  background: linear-gradient(135deg, rgba(255, 180, 0, 0.8), rgba(90, 124, 158, 0.35));
  box-shadow: 0 18px 42px rgba(13, 27, 42, 0.45);
}

.metric-card {
  padding: 14px;
  border-radius: 24px;
  background: rgba(13, 27, 42, 0.48);
  border: 1px solid rgba(195, 208, 221, 0.15);
  box-shadow:
    inset 0 1px 0 rgba(248, 250, 252, 0.08),
    0 12px 30px rgba(13, 27, 42, 0.32);
}

.glass-card,
.section-card,
.wallet-card {
  border-radius: 30px;
  background: rgba(225, 231, 238, 0.1);
  border: 1px solid rgba(195, 208, 221, 0.16);
  box-shadow:
    0 20px 58px rgba(13, 27, 42, 0.52),
    inset 0 1px 0 rgba(248, 250, 252, 0.12);
}

.section-card {
  padding: 18px;
}

.wallet-card {
  padding: 20px;
  background:
    linear-gradient(135deg, rgba(90, 124, 158, 0.28), rgba(40, 56, 69, 0.62)),
    rgba(225, 231, 238, 0.1);
}

.wallet-money {
  font-size: 2.2rem;
  line-height: 2.6rem;
  font-weight: 800;
  color: #ffb400;
  letter-spacing: -0.04em;
}

.info-row,
.record-row,
.message-row,
.setting-row {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 14px 0;
  border-bottom: 1px solid rgba(195, 208, 221, 0.1);
}

.info-row:last-child,
.record-row:last-child,
.message-row:last-child,
.setting-row:last-child {
  border-bottom: 0;
}

.row-icon {
  width: 42px;
  height: 42px;
  border-radius: 18px;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 1px solid rgba(195, 208, 221, 0.12);
}

.mini-panel {
  padding: 13px;
  border-radius: 22px;
  background: rgba(13, 27, 42, 0.42);
  border: 1px solid rgba(195, 208, 221, 0.12);
}

.invite-code-card {
  padding: 16px;
  border-radius: 26px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  background:
    linear-gradient(135deg, rgba(255, 180, 0, 0.18), rgba(90, 124, 158, 0.1)),
    rgba(13, 27, 42, 0.46);
  border: 1px solid rgba(255, 180, 0, 0.22);
}

.invite-code {
  font-size: 1.4rem;
  line-height: 1.82rem;
  font-weight: 800;
  color: #ffb400;
  letter-spacing: 0.08em;
}

.rule-card {
  padding: 14px;
  border-radius: 24px;
  background: rgba(255, 180, 0, 0.1);
  border: 1px solid rgba(255, 180, 0, 0.18);
}

.empty-state {
  padding: 22px 14px;
  border-radius: 24px;
  text-align: center;
  background: rgba(13, 27, 42, 0.34);
  border: 1px dashed rgba(195, 208, 221, 0.18);
}

.filter-chip {
  flex-shrink: 0;
  padding: 8px 14px;
  border-radius: 999px;
  color: #cbd5e1;
  background: rgba(225, 231, 238, 0.08);
  border: 1px solid rgba(195, 208, 221, 0.14);
}

.filter-chip-active {
  color: #fff0cc;
  background: rgba(255, 180, 0, 0.18);
  border-color: rgba(255, 180, 0, 0.36);
}

.message-dot {
  width: 9px;
  height: 9px;
  border-radius: 999px;
  flex-shrink: 0;
}

.message-dot-unread {
  background: #f37373;
  box-shadow: 0 0 18px rgba(243, 115, 115, 0.72);
}

.message-dot-read {
  background: #64748b;
}

.text-link {
  padding: 7px 12px;
  border-radius: 999px;
  background: rgba(255, 180, 0, 0.12);
  border: 1px solid rgba(255, 180, 0, 0.22);
}

.btn-core,
.btn-secondary,
.popup-btn-primary,
.popup-btn-secondary {
  position: relative;
  height: 52px;
  border-radius: 22px;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 10px;
  box-sizing: border-box;
}

.btn-core,
.popup-btn-primary {
  overflow: hidden;
  background: linear-gradient(135deg, #5a7c9e, #283845 48%, #0d1b2a);
  box-shadow:
    0 18px 42px rgba(13, 27, 42, 0.58),
    inset 0 1px 0 rgba(248, 250, 252, 0.22),
    inset 0 -1px 0 rgba(13, 27, 42, 0.45);
}

.btn-secondary,
.popup-btn-secondary {
  background: rgba(225, 231, 238, 0.12);
  border: 1px solid rgba(195, 208, 221, 0.18);
  box-shadow:
    0 14px 36px rgba(13, 27, 42, 0.36),
    inset 0 1px 0 rgba(248, 250, 252, 0.12);
}

.btn-shine {
  position: absolute;
  top: 0;
  left: 14px;
  right: 14px;
  height: 1px;
  background: linear-gradient(90deg, transparent, rgba(248, 250, 252, 0.75), transparent);
}

.btn-text,
.popup-btn-text {
  position: relative;
  z-index: 1;
  font-size: 1rem;
  line-height: 1.35rem;
  font-weight: 600;
  color: #f8fafc;
}

.btn-secondary-text,
.popup-btn-secondary-text {
  font-size: 1rem;
  line-height: 1.35rem;
  font-weight: 600;
  color: #e2e8f0;
}

.active-card:active,
.active-press:active {
  transform: scale(0.985);
  opacity: 0.92;
}

.loading-card {
  display: flex;
  align-items: center;
  padding: 16px;
  border-radius: 28px;
  background: rgba(225, 231, 238, 0.12);
  border: 1px solid rgba(195, 208, 221, 0.18);
  box-shadow:
    0 20px 58px rgba(13, 27, 42, 0.58),
    inset 0 1px 0 rgba(248, 250, 252, 0.14);
}

.loading-orbit {
  position: relative;
  width: 58px;
  height: 58px;
  border-radius: 999px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.loading-ring {
  position: absolute;
  inset: 0;
  border-radius: 999px;
  border: 2px solid rgba(195, 208, 221, 0.16);
  border-top-color: #ffb400;
  border-right-color: #42b861;
  animation: loadingRotate 1s linear infinite;
}

.loading-core {
  width: 42px;
  height: 42px;
  border-radius: 18px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: rgba(13, 27, 42, 0.72);
  border: 1px solid rgba(255, 180, 0, 0.26);
}

.popup-form,
.popup-panel {
  box-shadow:
    0 28px 90px rgba(13, 27, 42, 0.48),
    inset 0 1px 0 rgba(248, 250, 252, 0.4);
}

.amount-chip {
  height: 40px;
  border-radius: 16px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #283845;
  font-size: 0.8rem;
  font-weight: 700;
  background: #f1f5f9;
  border: 1px solid #e2e8f0;
}

.message-content-box {
  min-height: 120px;
  padding: 14px;
  border-radius: 22px;
  background: #f8fafc;
  border: 1px solid #e2e8f0;
}

.poster-card {
  position: relative;
  overflow: hidden;
  min-height: 260px;
  border-radius: 30px;
  background: #0d1b2a;
}

.poster-code {
  padding: 14px;
  border-radius: 22px;
  background: rgba(13, 27, 42, 0.58);
  border: 1px solid rgba(255, 180, 0, 0.24);
}

@keyframes loadingRotate {
  from {
    transform: rotate(0deg);
  }

  to {
    transform: rotate(360deg);
  }
}

/* 全局统一穿透：兼容 uni-easyinput 内部结构 */
:deep(.uni-easyinput__content) {
  min-height: 52px;
  border-radius: 20px;
  background-color: #f8fafc !important;
  border-color: #e2e8f0 !important;
}

:deep(.uni-easyinput__content-input) {
  color: #0f172a;
  font-size: 0.8rem;
}

:deep(.uni-easyinput__content-textarea) {
  color: #0f172a;
  font-size: 0.8rem;
  line-height: 1.2rem;
}

:deep(.uni-input-placeholder),
:deep(.uni-textarea-placeholder) {
  color: #64748b;
}

:deep(.uni-forms-item__label) {
  color: #334155;
  font-size: 0.65rem;
  font-weight: 600;
}
</style>