import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ICBCREProcess, NewCBCREProcess } from '../cbcre-process.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICBCREProcess for edit and NewCBCREProcessFormGroupInput for create.
 */
type CBCREProcessFormGroupInput = ICBCREProcess | PartialWithRequiredKeyOf<NewCBCREProcess>;

type CBCREProcessFormDefaults = Pick<NewCBCREProcess, 'id'>;

type CBCREProcessFormGroupContent = {
  id: FormControl<ICBCREProcess['id'] | NewCBCREProcess['id']>;
  cbProcessId: FormControl<ICBCREProcess['cbProcessId']>;
  cbProcessUlidId: FormControl<ICBCREProcess['cbProcessUlidId']>;
  cbProcessRefNo: FormControl<ICBCREProcess['cbProcessRefNo']>;
  anchorTraderId: FormControl<ICBCREProcess['anchorTraderId']>;
  anchorTraderGst: FormControl<ICBCREProcess['anchorTraderGst']>;
  financeAmount: FormControl<ICBCREProcess['financeAmount']>;
  processDateTime: FormControl<ICBCREProcess['processDateTime']>;
  creProcessStatus: FormControl<ICBCREProcess['creProcessStatus']>;
  responseDateTime: FormControl<ICBCREProcess['responseDateTime']>;
  creRequestId: FormControl<ICBCREProcess['creRequestId']>;
  cumilativeTradeScore: FormControl<ICBCREProcess['cumilativeTradeScore']>;
  timestamp: FormControl<ICBCREProcess['timestamp']>;
  totalAmountRequested: FormControl<ICBCREProcess['totalAmountRequested']>;
  totalInvoiceAmount: FormControl<ICBCREProcess['totalInvoiceAmount']>;
  status: FormControl<ICBCREProcess['status']>;
  financeRequest: FormControl<ICBCREProcess['financeRequest']>;
};

export type CBCREProcessFormGroup = FormGroup<CBCREProcessFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class CBCREProcessFormService {
  createCBCREProcessFormGroup(cBCREProcess: CBCREProcessFormGroupInput = { id: null }): CBCREProcessFormGroup {
    const cBCREProcessRawValue = {
      ...this.getFormDefaults(),
      ...cBCREProcess,
    };
    return new FormGroup<CBCREProcessFormGroupContent>({
      id: new FormControl(
        { value: cBCREProcessRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      cbProcessId: new FormControl(cBCREProcessRawValue.cbProcessId),
      cbProcessUlidId: new FormControl(cBCREProcessRawValue.cbProcessUlidId),
      cbProcessRefNo: new FormControl(cBCREProcessRawValue.cbProcessRefNo),
      anchorTraderId: new FormControl(cBCREProcessRawValue.anchorTraderId),
      anchorTraderGst: new FormControl(cBCREProcessRawValue.anchorTraderGst),
      financeAmount: new FormControl(cBCREProcessRawValue.financeAmount),
      processDateTime: new FormControl(cBCREProcessRawValue.processDateTime),
      creProcessStatus: new FormControl(cBCREProcessRawValue.creProcessStatus),
      responseDateTime: new FormControl(cBCREProcessRawValue.responseDateTime),
      creRequestId: new FormControl(cBCREProcessRawValue.creRequestId),
      cumilativeTradeScore: new FormControl(cBCREProcessRawValue.cumilativeTradeScore),
      timestamp: new FormControl(cBCREProcessRawValue.timestamp),
      totalAmountRequested: new FormControl(cBCREProcessRawValue.totalAmountRequested),
      totalInvoiceAmount: new FormControl(cBCREProcessRawValue.totalInvoiceAmount),
      status: new FormControl(cBCREProcessRawValue.status),
      financeRequest: new FormControl(cBCREProcessRawValue.financeRequest),
    });
  }

  getCBCREProcess(form: CBCREProcessFormGroup): ICBCREProcess | NewCBCREProcess {
    return form.getRawValue() as ICBCREProcess | NewCBCREProcess;
  }

  resetForm(form: CBCREProcessFormGroup, cBCREProcess: CBCREProcessFormGroupInput): void {
    const cBCREProcessRawValue = { ...this.getFormDefaults(), ...cBCREProcess };
    form.reset(
      {
        ...cBCREProcessRawValue,
        id: { value: cBCREProcessRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): CBCREProcessFormDefaults {
    return {
      id: null,
    };
  }
}
