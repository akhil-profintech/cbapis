import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IAction } from 'app/entities/action/action.model';
import { ActionService } from 'app/entities/action/service/action.service';
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

  actionsSharedCollection: IAction[] = [];

  editForm: ContextFormGroup = this.contextFormService.createContextFormGroup();

  constructor(
    protected contextService: ContextService,
    protected contextFormService: ContextFormService,
    protected actionService: ActionService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  compareAction = (o1: IAction | null, o2: IAction | null): boolean => this.actionService.compareAction(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ context }) => {
      this.context = context;
      if (context) {
        this.updateForm(context);
      }

      this.loadRelationshipsOptions();
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

    this.actionsSharedCollection = this.actionService.addActionToCollectionIfMissing<IAction>(this.actionsSharedCollection, context.action);
  }

  protected loadRelationshipsOptions(): void {
    this.actionService
      .query()
      .pipe(map((res: HttpResponse<IAction[]>) => res.body ?? []))
      .pipe(map((actions: IAction[]) => this.actionService.addActionToCollectionIfMissing<IAction>(actions, this.context?.action)))
      .subscribe((actions: IAction[]) => (this.actionsSharedCollection = actions));
  }
}
