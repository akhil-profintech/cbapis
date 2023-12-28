import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IContext, NewContext } from '../context.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IContext for edit and NewContextFormGroupInput for create.
 */
type ContextFormGroupInput = IContext | PartialWithRequiredKeyOf<NewContext>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IContext | NewContext> = Omit<T, 'transactionDate'> & {
  transactionDate?: string | null;
};

type ContextFormRawValue = FormValueOf<IContext>;

type NewContextFormRawValue = FormValueOf<NewContext>;

type ContextFormDefaults = Pick<NewContext, 'id' | 'transactionDate'>;

type ContextFormGroupContent = {
  id: FormControl<ContextFormRawValue['id'] | NewContext['id']>;
  transactionId: FormControl<ContextFormRawValue['transactionId']>;
  transactionDate: FormControl<ContextFormRawValue['transactionDate']>;
  clientId: FormControl<ContextFormRawValue['clientId']>;
  cpCode: FormControl<ContextFormRawValue['cpCode']>;
  action: FormControl<ContextFormRawValue['action']>;
};

export type ContextFormGroup = FormGroup<ContextFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ContextFormService {
  createContextFormGroup(context: ContextFormGroupInput = { id: null }): ContextFormGroup {
    const contextRawValue = this.convertContextToContextRawValue({
      ...this.getFormDefaults(),
      ...context,
    });
    return new FormGroup<ContextFormGroupContent>({
      id: new FormControl(
        { value: contextRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      transactionId: new FormControl(contextRawValue.transactionId, {
        validators: [Validators.required],
      }),
      transactionDate: new FormControl(contextRawValue.transactionDate, {
        validators: [Validators.required],
      }),
      clientId: new FormControl(contextRawValue.clientId, {
        validators: [Validators.required],
      }),
      cpCode: new FormControl(contextRawValue.cpCode, {
        validators: [Validators.required],
      }),
      action: new FormControl(contextRawValue.action),
    });
  }

  getContext(form: ContextFormGroup): IContext | NewContext {
    return this.convertContextRawValueToContext(form.getRawValue() as ContextFormRawValue | NewContextFormRawValue);
  }

  resetForm(form: ContextFormGroup, context: ContextFormGroupInput): void {
    const contextRawValue = this.convertContextToContextRawValue({ ...this.getFormDefaults(), ...context });
    form.reset(
      {
        ...contextRawValue,
        id: { value: contextRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): ContextFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      transactionDate: currentTime,
    };
  }

  private convertContextRawValueToContext(rawContext: ContextFormRawValue | NewContextFormRawValue): IContext | NewContext {
    return {
      ...rawContext,
      transactionDate: dayjs(rawContext.transactionDate, DATE_TIME_FORMAT),
    };
  }

  private convertContextToContextRawValue(
    context: IContext | (Partial<NewContext> & ContextFormDefaults),
  ): ContextFormRawValue | PartialWithRequiredKeyOf<NewContextFormRawValue> {
    return {
      ...context,
      transactionDate: context.transactionDate ? context.transactionDate.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
