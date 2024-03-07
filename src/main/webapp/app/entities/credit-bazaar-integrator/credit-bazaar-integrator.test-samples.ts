import { ICreditBazaarIntegrator, NewCreditBazaarIntegrator } from './credit-bazaar-integrator.model';

export const sampleWithRequiredData: ICreditBazaarIntegrator = {
  id: 22160,
  tenantId: 'meh',
  orgId: 'nuke pantyhose',
  customerName: 'ugh boo pheromone',
  orgName: 'motorcycle',
  gstId: 'outlandish determined quaintly',
  phoneNumber: 23223,
};

export const sampleWithPartialData: ICreditBazaarIntegrator = {
  id: 25456,
  tenantId: 'whoever',
  orgId: 'hepatitis hourly',
  customerName: 'pound positively usually',
  orgName: 'bah greedily untidy',
  gstId: 'gratefully robe gosh',
  phoneNumber: 7348,
};

export const sampleWithFullData: ICreditBazaarIntegrator = {
  id: 27799,
  integratorUlidId: 'yuck annually',
  tenantId: 'alley',
  orgId: 'eek',
  customerName: 'nautical slobber',
  orgName: 'wary',
  gstId: 'regulator',
  phoneNumber: 29259,
};

export const sampleWithNewData: NewCreditBazaarIntegrator = {
  tenantId: 'firsthand unbutton famously',
  orgId: 'the duh',
  customerName: 'before seep',
  orgName: 'boo giggle',
  gstId: 'vitro',
  phoneNumber: 160,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
