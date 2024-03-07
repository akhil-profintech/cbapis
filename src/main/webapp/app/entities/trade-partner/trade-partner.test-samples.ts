import { ITradePartner, NewTradePartner } from './trade-partner.model';

export const sampleWithRequiredData: ITradePartner = {
  id: 11166,
  orgId: 'concerning campaign',
  tenantId: 'noisily hearten regionalism',
  customerName: 'grouchy uh-huh',
  orgName: 'invade yum pita',
  gstId: 'fasten witty workbench',
  phoneNumber: 922,
};

export const sampleWithPartialData: ITradePartner = {
  id: 11498,
  tpId: 16755,
  orgId: 'which yum well-documented',
  tenantId: 'gigantic',
  customerName: 'convoke',
  orgName: 'valiantly extremely',
  gstId: 'subdued',
  phoneNumber: 19942,
  tradePartnerName: 'whoever',
  location: 'notwithstanding diluted despite',
  tradePartnerGST: 'the but',
};

export const sampleWithFullData: ITradePartner = {
  id: 21672,
  tpId: 27759,
  tpUlidId: 'woot readily now',
  orgId: 'because',
  tenantId: 'creche up rise',
  customerName: 'punctually off',
  orgName: 'astride for',
  gstId: 'urge meh',
  phoneNumber: 31586,
  tradePartnerName: 'as when zowie',
  location: 'spend whenever',
  tradePartnerGST: 'usually hence',
  tradePartnerSector: 'repeatedly but hmph',
  acceptanceFromTradePartner: 'variety',
  tosDocument: 'ornery',
};

export const sampleWithNewData: NewTradePartner = {
  orgId: 'acidly how inwardly',
  tenantId: 'merrily coordinated',
  customerName: 'within',
  orgName: 'after',
  gstId: 'humongous final',
  phoneNumber: 28098,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
