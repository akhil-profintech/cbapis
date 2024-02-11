import { ITenantDetails, NewTenantDetails } from './tenant-details.model';

export const sampleWithRequiredData: ITenantDetails = {
  id: 11254,
};

export const sampleWithPartialData: ITenantDetails = {
  id: 15379,
  tenantId: 'objection',
};

export const sampleWithFullData: ITenantDetails = {
  id: 10300,
  tenantId: 'miserably',
  tenantSchema: 'thorough officiate even',
};

export const sampleWithNewData: NewTenantDetails = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
