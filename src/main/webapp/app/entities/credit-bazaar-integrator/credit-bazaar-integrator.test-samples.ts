import { ICreditBazaarIntegrator, NewCreditBazaarIntegrator } from './credit-bazaar-integrator.model';

export const sampleWithRequiredData: ICreditBazaarIntegrator = {
  id: 22160,
  tenantId: 'meh',
  integratorId: 'nuke pantyhose',
  orgId: 'ugh boo pheromone',
  customerName: 'motorcycle',
  orgName: 'outlandish determined quaintly',
  gstId: 'until drape whereas',
  phoneNumber: 22512,
};

export const sampleWithPartialData: ICreditBazaarIntegrator = {
  id: 4632,
  tenantId: 'bookmark renovate',
  integratorId: 'against',
  orgId: 'hence forgo gee',
  customerName: 'elevation passenger buff',
  orgName: 'honored object openly',
  gstId: 'cotton pledge patina',
  phoneNumber: 29243,
};

export const sampleWithFullData: ICreditBazaarIntegrator = {
  id: 31221,
  tenantId: 'what',
  integratorId: 'as webbed',
  orgId: 'temperature whenever gadzooks',
  customerName: 'a',
  orgName: 'plunk ew virtuous',
  gstId: 'low',
  phoneNumber: 890,
};

export const sampleWithNewData: NewCreditBazaarIntegrator = {
  tenantId: 'complain',
  integratorId: 'isogloss brake',
  orgId: 'brisk',
  customerName: 'for selfishly though',
  orgName: 'that high onset',
  gstId: 'wet oh',
  phoneNumber: 10745,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
