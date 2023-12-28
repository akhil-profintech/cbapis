import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IClientCodes, NewClientCodes } from '../client-codes.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IClientCodes for edit and NewClientCodesFormGroupInput for create.
 */
type ClientCodesFormGroupInput = IClientCodes | PartialWithRequiredKeyOf<NewClientCodes>;

type ClientCodesFormDefaults = Pick<NewClientCodes, 'id'>;

type ClientCodesFormGroupContent = {
  id: FormControl<IClientCodes['id'] | NewClientCodes['id']>;
  clientCode: FormControl<IClientCodes['clientCode']>;
  clientCharsCode: FormControl<IClientCodes['clientCharsCode']>;
  clientName: FormControl<IClientCodes['clientName']>;
};

export type ClientCodesFormGroup = FormGroup<ClientCodesFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ClientCodesFormService {
  createClientCodesFormGroup(clientCodes: ClientCodesFormGroupInput = { id: null }): ClientCodesFormGroup {
    const clientCodesRawValue = {
      ...this.getFormDefaults(),
      ...clientCodes,
    };
    return new FormGroup<ClientCodesFormGroupContent>({
      id: new FormControl(
        { value: clientCodesRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      clientCode: new FormControl(clientCodesRawValue.clientCode),
      clientCharsCode: new FormControl(clientCodesRawValue.clientCharsCode),
      clientName: new FormControl(clientCodesRawValue.clientName),
    });
  }

  getClientCodes(form: ClientCodesFormGroup): IClientCodes | NewClientCodes {
    return form.getRawValue() as IClientCodes | NewClientCodes;
  }

  resetForm(form: ClientCodesFormGroup, clientCodes: ClientCodesFormGroupInput): void {
    const clientCodesRawValue = { ...this.getFormDefaults(), ...clientCodes };
    form.reset(
      {
        ...clientCodesRawValue,
        id: { value: clientCodesRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): ClientCodesFormDefaults {
    return {
      id: null,
    };
  }
}
