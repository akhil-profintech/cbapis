import { IAction, NewAction } from './action.model';

export const sampleWithRequiredData: IAction = {
  id: 20341,
};

export const sampleWithPartialData: IAction = {
  id: 1108,
  actionId: 11311,
  actionCode: 'quaint perforate windy',
  actionVal: 'spotted',
  cpCode: 'as',
};

export const sampleWithFullData: IAction = {
  id: 4148,
  actionId: 14262,
  actionCode: 'safety amid showy',
  actionDescription: 'lift despite overfly',
  actionVal: 'masculine',
  cpCode: 'relegate upside-down issue',
};

export const sampleWithNewData: NewAction = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
