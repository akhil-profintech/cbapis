import { IUserDtls, NewUserDtls } from './user-dtls.model';

export const sampleWithRequiredData: IUserDtls = {
  id: 3183,
};

export const sampleWithPartialData: IUserDtls = {
  id: 25897,
  userName: 'whereas',
  tenantId: 'consequently',
  isTradePartnerEnabled: true,
  tradePartnerId: 'reluctantly bitterly gee',
  isFinancePartnerEnabled: true,
  defaultPersona: 'AnchorTrader',
};

export const sampleWithFullData: IUserDtls = {
  id: 15718,
  userId: 'successfully aside duh',
  userName: 'huzzah',
  tenantId: 'folder when',
  isAnchorTraderEnabled: true,
  anchorTraderId: 'drat scholarly',
  isTradePartnerEnabled: false,
  tradePartnerId: 'tape',
  isFinancePartnerEnabled: true,
  financePartnerId: 'before meh',
  defaultPersona: 'FinancePartner',
};

export const sampleWithNewData: NewUserDtls = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
