import { ITradeChannel, NewTradeChannel } from './trade-channel.model';

export const sampleWithRequiredData: ITradeChannel = {
  id: 12523,
};

export const sampleWithPartialData: ITradeChannel = {
  id: 9354,
  anchorTraderId: 'monthly',
};

export const sampleWithFullData: ITradeChannel = {
  id: 18866,
  tradeChannelId: 'meteor whereas authorized',
  anchorTraderId: 'around nitrify so',
  tradePartnerId: 'until questioningly jade',
  financePartnerId: 'ick electric aha',
  anchorTraderTenantId: 'impel',
  tradePartnerTenantId: 'psst which an',
  financePartnerTenantId: 'frail however without',
};

export const sampleWithNewData: NewTradeChannel = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
