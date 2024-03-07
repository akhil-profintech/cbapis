import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IContext } from '../context.model';
import { ContextService } from '../service/context.service';
import { ContextFormService, ContextFormGroup } from './context-form.service';

@Component({
  standalone: true,
  selector: 'jhi-context-update',
  templateUrl: './context-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class ContextUpdateComponent implements OnInit {
  isSaving = false;
  context: IContext | null = null;

  editForm: ContextFormGroup = this.contextFormService.createContextFormGroup();

  constructor(
    protected contextService: ContextService,
    protected contextFormService: ContextFormService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ context }) => {
      this.context = context;
      if (context) {
        this.updateForm(context);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const context = this.contextFormService.getContext(this.editForm);
    if (context.id !== null) {
      this.subscribeToSaveResponse(this.contextService.update(context));
    } else {
      this.subscribeToSaveResponse(this.contextService.create(context));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IContext>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(context: IContext): void {
    this.context = context;
    this.contextFormService.resetForm(this.editForm, context);
  }
}
