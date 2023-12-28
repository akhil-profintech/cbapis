import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IUpdateVA, NewUpdateVA } from '../update-va.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IUpdateVA for edit and NewUpdateVAFormGroupInput for create.
 */
type UpdateVAFormGroupInput = IUpdateVA | PartialWithRequiredKeyOf<NewUpdateVA>;

type UpdateVAFormDefaults = Pick<NewUpdateVA, 'id'>;

type UpdateVAFormGroupContent = {
  id: FormControl<IUpdateVA['id'] | NewUpdateVA['id']>;
  updateVirAccId: FormControl<IUpdateVA['updateVirAccId']>;
  finReqId: FormControl<IUpdateVA['finReqId']>;
  subGrpId: FormControl<IUpdateVA['subGrpId']>;
  msgId: FormControl<IUpdateVA['msgId']>;
  cnvId: FormControl<IUpdateVA['cnvId']>;
  extRefId: FormControl<IUpdateVA['extRefId']>;
  bizObjId: FormControl<IUpdateVA['bizObjId']>;
  appId: FormControl<IUpdateVA['appId']>;
  timestamp: FormControl<IUpdateVA['timestamp']>;
  vaNum: FormControl<IUpdateVA['vaNum']>;
  finPar: FormControl<IUpdateVA['finPar']>;
  clientCode: FormControl<IUpdateVA['clientCode']>;
  requestDateTime: FormControl<IUpdateVA['requestDateTime']>;
  reslt: FormControl<IUpdateVA['reslt']>;
  status: FormControl<IUpdateVA['status']>;
  statusDesc: FormControl<IUpdateVA['statusDesc']>;
  errorCode: FormControl<IUpdateVA['errorCode']>;
  responseDateTime: FormControl<IUpdateVA['responseDateTime']>;
  lastupdatedDateTime: FormControl<IUpdateVA['lastupdatedDateTime']>;
  lastUpdatedBy: FormControl<IUpdateVA['lastUpdatedBy']>;
  tradeEntity: FormControl<IUpdateVA['tradeEntity']>;
};

export type UpdateVAFormGroup = FormGroup<UpdateVAFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class UpdateVAFormService {
  createUpdateVAFormGroup(updateVA: UpdateVAFormGroupInput = { id: null }): UpdateVAFormGroup {
    const updateVARawValue = {
      ...this.getFormDefaults(),
      ...updateVA,
    };
    return new FormGroup<UpdateVAFormGroupContent>({
      id: new FormControl(
        { value: updateVARawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      updateVirAccId: new FormControl(updateVARawValue.updateVirAccId, {
        validators: [Validators.required],
      }),
      finReqId: new FormControl(updateVARawValue.finReqId),
      subGrpId: new FormControl(updateVARawValue.subGrpId),
      msgId: new FormControl(updateVARawValue.msgId),
      cnvId: new FormControl(updateVARawValue.cnvId),
      extRefId: new FormControl(updateVARawValue.extRefId),
      bizObjId: new FormControl(updateVARawValue.bizObjId),
      appId: new FormControl(updateVARawValue.appId),
      timestamp: new FormControl(updateVARawValue.timestamp),
      vaNum: new FormControl(updateVARawValue.vaNum),
      finPar: new FormControl(updateVARawValue.finPar),
      clientCode: new FormControl(updateVARawValue.clientCode),
      requestDateTime: new FormControl(updateVARawValue.requestDateTime, {
        validators: [Validators.required],
      }),
      reslt: new FormControl(updateVARawValue.reslt),
      status: new FormControl(updateVARawValue.status),
      statusDesc: new FormControl(updateVARawValue.statusDesc),
      errorCode: new FormControl(updateVARawValue.errorCode),
      responseDateTime: new FormControl(updateVARawValue.responseDateTime, {
        validators: [Validators.required],
      }),
      lastupdatedDateTime: new FormControl(updateVARawValue.lastupdatedDateTime, {
        validators: [Validators.required],
      }),
      lastUpdatedBy: new FormControl(updateVARawValue.lastUpdatedBy, {
        validators: [Validators.required],
      }),
      tradeEntity: new FormControl(updateVARawValue.tradeEntity),
    });
  }

  getUpdateVA(form: UpdateVAFormGroup): IUpdateVA | NewUpdateVA {
    return form.getRawValue() as IUpdateVA | NewUpdateVA;
  }

  resetForm(form: UpdateVAFormGroup, updateVA: UpdateVAFormGroupInput): void {
    const updateVARawValue = { ...this.getFormDefaults(), ...updateVA };
    form.reset(
      {
        ...updateVARawValue,
        id: { value: updateVARawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): UpdateVAFormDefaults {
    return {
      id: null,
    };
  }
}
